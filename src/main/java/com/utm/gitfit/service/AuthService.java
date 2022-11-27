package com.utm.gitfit.service;


import com.utm.gitfit.model.client.ApiException;
import com.utm.gitfit.model.client.api.CustomersApi;
import com.utm.gitfit.model.client.model.CreatedCustomerResponse;
import com.utm.gitfit.model.client.model.CustomerRequestBody;
import com.utm.gitfit.model.client.model.CustomerRequestBodyData;
import com.utm.gitfit.model.entities.Client;
import com.utm.gitfit.model.entities.Coach;
import com.utm.gitfit.model.entities.Role;
import com.utm.gitfit.model.entities.User;
import com.utm.gitfit.model.enums.ERole;
import com.utm.gitfit.model.request.AppUserType;
import com.utm.gitfit.model.request.LoginRequest;
import com.utm.gitfit.model.request.RegistrationRequest;
import com.utm.gitfit.model.response.JwtResponse;
import com.utm.gitfit.repository.RoleRepository;
import com.utm.gitfit.repository.UserRepository;
import com.utm.gitfit.security.JwtUtils;
import com.utm.gitfit.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomersApi customersApi;

    @Transactional(readOnly = true)
    public JwtResponse authenticateUser(final LoginRequest loginRequest) {
        return getJwtResponseForUserCredentials(loginRequest.email(), loginRequest.password());
    }

    private JwtResponse getJwtResponseForUserCredentials(String email, String password) {
        final var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final var jwt = jwtUtils.generateJwtToken(authentication);
        final var userDetails = (UserDetailsImpl) authentication.getPrincipal();
        final var roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return new JwtResponse(userDetails.id(), userDetails.email(), roles, jwt);
    }

    @Transactional
    public void registerUser(RegistrationRequest registrationRequest) throws ApiException {
        final Role role = roleRepository.findByName(ERole.valueOf(registrationRequest.userType().name()))
                .orElseThrow();
        final User user = registrationRequest.userType() == AppUserType.CLIENT ? new Client() : new Coach();
        user.setUserRole(role);
        user.setPassword(passwordEncoder.encode(registrationRequest.password()));
        user.setEmail(registrationRequest.email());
        user.setBirthday(registrationRequest.birthday());
        user.setUsername(registrationRequest.username());
        user.setName(registrationRequest.firstName());
        user.setLastName(registrationRequest.surname());
        final var customerResponse = createSaltEdgeCustomer(user.getEmail());
        user.setSaltEdgeIdentifier(customerResponse.getData().getId());

        userRepository.save(user);
    }

    private CreatedCustomerResponse createSaltEdgeCustomer(final String email) throws ApiException {
        final var customerRequestBody = new CustomerRequestBody();
        final var customerRequestBodyData = new CustomerRequestBodyData();
        customerRequestBodyData.setIdentifier(email);
        customerRequestBody.setData(customerRequestBodyData);

        return customersApi.customersPost(customerRequestBody);
    }
}
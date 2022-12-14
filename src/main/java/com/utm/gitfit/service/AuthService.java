package com.utm.gitfit.service;


import com.utm.gitfit.exception.AuthException;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public void registerUser(RegistrationRequest registrationRequest) {
        final Role role = roleRepository.findByName(ERole.valueOf(registrationRequest.userType().name()))
                .orElseThrow();
        final User user = registrationRequest.userType() == AppUserType.CLIENT ? new Client() : new Coach();
        user.setUserRole(role);
        user.setPassword(validatePassword(registrationRequest.password()));
        user.setEmail(validateEmail(registrationRequest.email()));
        user.setBirthday(registrationRequest.birthday());
        user.setUsername(registrationRequest.username());
        user.setName(registrationRequest.firstName());
        user.setLastName(registrationRequest.surname());
        try {
            final var customerResponse = createSaltEdgeCustomer(user.getEmail());
            user.setSaltEdgeIdentifier(customerResponse.getData().getId());

            userRepository.save(user);
        } catch (ApiException e) {
            throw new AuthException("Email: " + user.getEmail() + ", already in use.");
        }
    }

    private CreatedCustomerResponse createSaltEdgeCustomer(final String email) throws ApiException {
        final var customerRequestBody = new CustomerRequestBody();
        final var customerRequestBodyData = new CustomerRequestBodyData();
        customerRequestBodyData.setIdentifier(email);
        customerRequestBody.setData(customerRequestBodyData);

        return customersApi.customersPost(customerRequestBody);
    }

    private String validatePassword(String password){
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

        if(isValid(password, passwordRegex))
            return passwordEncoder.encode(password);
        else throw new AuthException("Your password doesn't meet the requirements. " +
                "A password must contain one numeric, lowercase, uppercase and special symbol, while having a length of between 8 and 20 characters.");
    }

    private String validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";

        if(isValid(email, emailRegex))
            return email;
        else throw new AuthException("Your email doesn't abide the OWASP requirements.");
    }

    private boolean isValid(String toValidate, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(toValidate);
        return matcher.matches();
    }
}
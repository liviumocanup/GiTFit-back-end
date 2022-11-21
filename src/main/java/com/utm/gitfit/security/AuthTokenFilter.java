package com.utm.gitfit.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NotNull final HttpServletRequest request, @NotNull final HttpServletResponse response, @NotNull final FilterChain filterChain) {
        try {
            final var jwt = jwtUtils.parseJwt(request.getHeader(AUTHORIZATION));
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                UsernamePasswordAuthenticationToken authentication;
                    final var username = jwtUtils.getUsernameFromJwtToken(jwt);
                    final var userDetails = userDetailsService.loadUserByUsername(username);
                    authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            log.error("Cannot set user authentication: %s".formatted(e.getMessage()));
        }
    }
}
package com.utm.gitfit.controller;

import com.utm.gitfit.model.request.LoginRequest;
import com.utm.gitfit.model.request.RegistrationRequest;
import com.utm.gitfit.model.response.JwtResponse;
import com.utm.gitfit.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.authenticateUser(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> registerUser(@Valid @RequestBody RegistrationRequest registrationRequest){
        return ResponseEntity.ok(authService.registerUser(registrationRequest));
    }
}
package com.utm.gitfit.controller;

import com.utm.gitfit.model.request.LoginRequest;
import com.utm.gitfit.model.request.RegistrationRequest;
import com.utm.gitfit.model.response.JwtResponse;
import com.utm.gitfit.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.authenticateUser(loginRequest));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@Valid @RequestBody RegistrationRequest registrationRequest){
        authService.registerUser(registrationRequest);
    }
}
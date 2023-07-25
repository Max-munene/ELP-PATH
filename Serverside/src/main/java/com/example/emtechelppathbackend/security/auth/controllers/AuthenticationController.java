package com.example.emtechelppathbackend.security.auth.controllers;

import com.example.emtechelppathbackend.exceptions.UserDetailsNotFoundException;
import com.example.emtechelppathbackend.security.auth.AuthenticationRequest;
import com.example.emtechelppathbackend.security.auth.RegisterRequest;
import com.example.emtechelppathbackend.security.auth.AuthenticationResponse;
import com.example.emtechelppathbackend.security.auth.services.AuthenticationService;
import com.example.emtechelppathbackend.security.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest){
        if (userRepository.existsByUserEmail(registerRequest.getUserEmail())) {
            throw new UserDetailsNotFoundException("User with this email already exists");

        }
        return ResponseEntity.ok(service.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(service.authenticate(authenticationRequest));
    }
}

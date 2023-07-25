package com.example.emtechelppathbackend.security.auth.services;

import com.example.emtechelppathbackend.security.auth.AuthenticationRequest;
import com.example.emtechelppathbackend.security.auth.AuthenticationResponse;
import com.example.emtechelppathbackend.security.auth.RegisterRequest;
import com.example.emtechelppathbackend.security.entities.Role;
import com.example.emtechelppathbackend.security.entities.Users;
import com.example.emtechelppathbackend.security.repositories.RoleRepository;
import com.example.emtechelppathbackend.security.repositories.UserRepository;
import com.example.emtechelppathbackend.security.Jwtservices.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final RoleRepository roleRepository;


    //inject the jwt service to handle token generation
    @Autowired
    private final JwtService jwtService;

    // inject the password encoder service
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUserEmail(),
                        authenticationRequest.getUserPassword())
        );
        //if we get here, then the user is authenticated
        var user = repository.findByUserEmail(authenticationRequest.getUserEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .id(user.getId())
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .userEmail(authenticationRequest.getUserEmail())
                .token(jwtToken)
                .build();
    }

    //creating users, saving them to the database and returning the generated token out of it
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = Users.builder()
                .scholarNumber(registerRequest.getScholarNumber())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .userEmail(registerRequest.getUserEmail())
                .userPassword(passwordEncoder.encode(registerRequest.getUserPassword()))
                .build();
//        //Optional<Role> optionalRole = roleRepository.findByRoleName(registerRequest.getRole().getRoleName());
//        Role role;
//
//        if (optionalRole.isPresent()) {
//            // If the role exists, use it
//            role = optionalRole.get();
//        } else {
//            // If the role does not exist, create a new one
//            role = new Role();
//            role.setRoleName(registerRequest.getRole().getRoleName());
//            roleRepository.save(role);
//        }

// Set the role for the Users entity
        //user.setRole(role);
        System.out.println(user);

// Check if the role exists in the database //todo, change this to ensure all people are assigned to a role.
        Role role = registerRequest.getRole();

        if (role == null || role.getRoleName() == null) {
            role = roleRepository.findByRoleName("UNASSIGNED");
            if (role == null) {
                // If the "UNASSIGNED" role does not exist, create it
                role = new Role();
                role.setRoleName("UNASSIGNED");
                roleRepository.save(role);
            }
        } else {
            Role existingRole = roleRepository.findByRoleName(role.getRoleName());
            if (existingRole == null) {
                throw new IllegalStateException("Specified role does not exist. Please create the role first.");
            }
            role = existingRole;
        }

// Set the role for the Users entity
        user.setRole(role);

// Save the Users entity
        repository.save(user);
        //returning the authentication response that contains the token
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .id(user.getId())
                .token(jwtToken)
                .userEmail(registerRequest.getUserEmail())
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .build();


    }
}

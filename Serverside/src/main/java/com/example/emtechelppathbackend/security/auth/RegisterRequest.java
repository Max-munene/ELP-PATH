package com.example.emtechelppathbackend.security.auth;

import com.example.emtechelppathbackend.security.entities.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {
    private Long id;

    private String scholarNumber;

    private String firstName;

    private String lastName;

    private String userEmail;

    private String userPassword;
    @JsonProperty
    private Role role;


}

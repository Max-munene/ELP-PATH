package com.example.emtechelppathbackend.dtos.userProfile;

import com.example.emtechelppathbackend.security.entities.Role;


import lombok.Data;

@Data
public class UsersDto {
    private Long id;

    private String scholarNumber;

    private String firstName;

    private String lastName;

    private String userEmail;

    private Role role;
}

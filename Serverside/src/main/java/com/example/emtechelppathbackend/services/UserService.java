package com.example.emtechelppathbackend.services;

import com.example.emtechelppathbackend.dtos.userProfile.UsersDto;

import com.example.emtechelppathbackend.security.entities.Users;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    UsersDto getUserDetails(Long userId,UsersDto usersDto);
    List<Users> viewAllUsers();
}

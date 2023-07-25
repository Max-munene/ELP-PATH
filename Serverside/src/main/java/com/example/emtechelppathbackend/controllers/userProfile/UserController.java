package com.example.emtechelppathbackend.controllers.userProfile;

import com.example.emtechelppathbackend.dtos.userProfile.UsersDto;
import com.example.emtechelppathbackend.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final ModelMapper modelMapper;
    private final UserService userService;

    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/view")
    public ResponseEntity<List<UsersDto>>viewAllUsers(){
        return ResponseEntity.ok(userService.viewAllUsers().stream()
                .map(users -> modelMapper.map(users, UsersDto.class))
                .collect(Collectors.toList()));
    }
}

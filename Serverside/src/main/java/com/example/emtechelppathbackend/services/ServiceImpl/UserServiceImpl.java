package com.example.emtechelppathbackend.services.ServiceImpl;


import com.example.emtechelppathbackend.dtos.userProfile.UsersDto;

import com.example.emtechelppathbackend.repositories.ProfileRepo;
import com.example.emtechelppathbackend.security.entities.Users;
import com.example.emtechelppathbackend.security.repositories.UserRepository;
import com.example.emtechelppathbackend.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final ProfileRepo profileRepo;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(ProfileRepo profileRepo, ModelMapper modelMapper, UserRepository userRepository) {
        this.profileRepo = profileRepo;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UsersDto getUserDetails(Long userId, UsersDto usersDto) {
        return null;
    }

    @Override
    public List<Users> viewAllUsers() {
        List<Users> users = userRepository.findAll();
        return users;
    }

    private UsersDto mapToDto(Users users){
        UsersDto usersDto = new UsersDto();
        usersDto.setId(users.getId());
        usersDto.setFirstName(users.getFirstName());
        usersDto.setLastName(users.getLastName());
        usersDto.setUserEmail(users.getUserEmail());

        return usersDto;
    }
    private Users mapToEntity(UsersDto usersDto){
        Users users = new Users();
        users.setId(usersDto.getId());
        users.setFirstName(usersDto.getFirstName());
        users.setLastName(usersDto.getLastName());
        users.setUserEmail(usersDto.getUserEmail());

        return users;
    }
}

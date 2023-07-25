package com.example.emtechelppathbackend.services.ServiceImpl;

import com.example.emtechelppathbackend.dtos.userProfile.ProfileDto;
import com.example.emtechelppathbackend.entities.userProfile.Profile;

import com.example.emtechelppathbackend.exceptions.UserDetailsNotFoundException;
import com.example.emtechelppathbackend.repositories.ProfileRepo;
import com.example.emtechelppathbackend.security.entities.Users;
import com.example.emtechelppathbackend.security.repositories.UserRepository;

import com.example.emtechelppathbackend.services.ProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class profileServiceImpl implements ProfileService {
    private final ProfileRepo profileRepo;
    private final UserRepository userRepository;

    public profileServiceImpl(ProfileRepo profileRepo, UserRepository userRepository) {
        this.profileRepo = profileRepo;
        this.userRepository = userRepository;
    }

    @Override
    public ProfileDto createProfile(ProfileDto profileDto,Long userId) {
        Profile profile = mapToEntity(profileDto);
        Users users = userRepository.findById(userId).orElseThrow(()->new UserDetailsNotFoundException("User with that id not found"));
        if (users.getProfile() != null){
            throw  new UserDetailsNotFoundException("your profile exist you can update it");
        }
        profile.setUser(users);
        profile.setId(profileDto.getId());
        profile.setEmail(profileDto.getEmail());
        profile.setPhoneNo(profileDto.getPhoneNo());
        profile.setWebsite(profileDto.getWebsite());
        Profile profile1 = profileRepo.save(profile);
        return mapToDto(profile1);
    }

    @Override
    public List<ProfileDto> getProfileByUserId(Long userId) {
        List<Profile> profiles = profileRepo.findProfileByUserId(userId);
        return profiles.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public ProfileDto updateProfile(Long userId,Long profileId, ProfileDto profileDto) {
        Users users = userRepository.findById(userId).orElseThrow(()->new UserDetailsNotFoundException("user not found"));
        Profile profile = profileRepo.findById(profileId).orElseThrow(()->new UserDetailsNotFoundException("profile of this user not found"));
        if (!Objects.equals(profile.getUser().getId(), users.getId())){
            throw  new UserDetailsNotFoundException("this profile does not belong to this user");
        }
        profile.setWebsite(profileDto.getWebsite());
        profile.setEmail(profileDto.getEmail());
        profile.setTitle(profileDto.getTitle());
        profile.setPhoneNo(profileDto.getPhoneNo());
        Profile updatedProfile = profileRepo.save(profile);
        return mapToDto(updatedProfile);
    }

    @Override
    public ProfileDto updateProfileByUserId(Long userId, ProfileDto profileDto) {
        Users users = userRepository.findById(userId).orElseThrow(()->new UserDetailsNotFoundException("user not found"));
        Profile profile = users.getProfile();
        if (profile == null){
            throw  new UserDetailsNotFoundException("profile not found for the user");
        }
        profile.setWebsite(profileDto.getWebsite());
        profile.setEmail(profileDto.getEmail());
        profile.setTitle(profileDto.getTitle());
        profile.setPhoneNo(profileDto.getPhoneNo());
        Profile updatedProfile = profileRepo.save(profile);
        return mapToDto(updatedProfile);

    }

    @Override
    public void deleteProfile(Long profileId, Long userId) {

        Users users = userRepository.findById(userId).orElseThrow(()->new UserDetailsNotFoundException("user not found"));
        Profile profile = profileRepo.findById(profileId).orElseThrow(()->new UserDetailsNotFoundException("profile not found"));
        if (!Objects.equals(profile.getUser().getId(),users.getId())){
            throw new UserDetailsNotFoundException("This profile is not yours");
        }
        profileRepo.delete(profile);
    }


    private ProfileDto mapToDto(Profile profile){
        ProfileDto profileDto = new ProfileDto();
        profileDto.setId(profile.getId());
        profileDto.setTitle(profile.getTitle());
        profileDto.setWebsite(profile.getWebsite());

        profileDto.setEmail(profile.getEmail());

        profileDto.setPhoneNo(profile.getPhoneNo());
        return profileDto;
    }
    private Profile mapToEntity(ProfileDto profileDto){
        Profile profile = new Profile();
        profile.setId(profileDto.getId());
        return profile;
    }
}

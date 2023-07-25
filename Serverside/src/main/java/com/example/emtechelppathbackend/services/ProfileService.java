package com.example.emtechelppathbackend.services;


import com.example.emtechelppathbackend.dtos.userProfile.ProfileDto;


import java.util.List;

public interface ProfileService {
    ProfileDto createProfile(ProfileDto profileDto,Long userId);
    List<ProfileDto> getProfileByUserId(Long userId);
    ProfileDto updateProfile(Long userId,Long profileId,ProfileDto profileDto);
    ProfileDto updateProfileByUserId(Long userId,ProfileDto profileDto);
    void deleteProfile(Long profileId,Long userId);

}

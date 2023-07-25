package com.example.emtechelppathbackend.controllers.userProfile;

import com.example.emtechelppathbackend.dtos.userProfile.ProfileDto;
import com.example.emtechelppathbackend.services.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }
    @PostMapping("/{userId}/create")
    public ResponseEntity<ProfileDto> createProfile(@RequestBody ProfileDto profileDto,@PathVariable(value = "userId") Long userId){
        return new ResponseEntity<>(profileService.createProfile(profileDto,userId), HttpStatus.CREATED);
    }
    @GetMapping("/{userId}/view")
    public List<ProfileDto> getProfileByUserIS(@PathVariable(value = "userId")Long userId){
        return profileService.getProfileByUserId(userId);
    }
    @PutMapping("/{userId}/{profileId}/update")
    public ResponseEntity<ProfileDto>updateProfile(@PathVariable(value = "userId")Long userId,@PathVariable(value = "profileId")Long profileId,@RequestBody ProfileDto profileDto){
        ProfileDto newProfile = profileService.updateProfile(userId, profileId, profileDto);
        return new ResponseEntity<>(newProfile,HttpStatus.OK);
    }
    @PutMapping("/{userId}/update")
    public ResponseEntity<ProfileDto>updateUserProfile(@PathVariable(value = "userId")Long userId,@RequestBody ProfileDto profileDto){
        ProfileDto newProfile = profileService.updateProfileByUserId(userId, profileDto);
        return new ResponseEntity<>(newProfile,HttpStatus.OK);
    }
    @DeleteMapping("/{userId}/{profileId}/delete")
    public ResponseEntity<?> deleteProfile(@PathVariable(value = "userId")Long userId,@PathVariable(value = "profileId")Long profileId){
        profileService.deleteProfile(userId, profileId);
        return new ResponseEntity<>("profile deleted successfully",HttpStatus.OK);
    }
}

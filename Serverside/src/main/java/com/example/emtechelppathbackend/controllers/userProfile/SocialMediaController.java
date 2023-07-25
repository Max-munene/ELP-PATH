package com.example.emtechelppathbackend.controllers.userProfile;

import com.example.emtechelppathbackend.dtos.userProfile.SocialMediaDto;
import com.example.emtechelppathbackend.services.SocialMediaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/socials")
public class SocialMediaController {
    private final SocialMediaService socialMediaService;

    public SocialMediaController(SocialMediaService socialMediaService) {
        this.socialMediaService = socialMediaService;
    }
    @PostMapping("/{userId}/add")
    public ResponseEntity<SocialMediaDto>addSocialMedia(@PathVariable(value = "userId")Long userId, @RequestBody SocialMediaDto socialMediaDto){
        return  new ResponseEntity<>(socialMediaService.addSocialMediaLink(socialMediaDto,userId), HttpStatus.CREATED);
    }
    @GetMapping("/{userId}/view")
    public List<SocialMediaDto> viewSocialMedia(@PathVariable(value = "userId")Long userId){
        return socialMediaService.viewSocialMediaByUserId(userId);

    }
    @PutMapping("/{userId}/update")
    public ResponseEntity<SocialMediaDto> updateSocialMedia(@PathVariable(value = "userId")Long userId,@RequestBody SocialMediaDto socialMediaDto){
        SocialMediaDto updatedSocials = socialMediaService.updateSocialMediaByUserId(userId, socialMediaDto);
        return new ResponseEntity<>(updatedSocials,HttpStatus.OK);
    }
}

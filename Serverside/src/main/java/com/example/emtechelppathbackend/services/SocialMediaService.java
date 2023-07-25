package com.example.emtechelppathbackend.services;
import com.example.emtechelppathbackend.dtos.userProfile.SocialMediaDto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SocialMediaService {
    SocialMediaDto addSocialMediaLink(SocialMediaDto socialMediaDto,Long userId);
    List<SocialMediaDto> viewSocialMediaByUserId(Long userId);

    SocialMediaDto updateSocialMedia(Long userId,SocialMediaDto socialMediaDto);
    SocialMediaDto updateSocialMediaByUserId(Long userId,SocialMediaDto socialMediaDto);
    void deleteSocialMedia(Long socialId,Long userId);
}

package com.example.emtechelppathbackend.services.ServiceImpl;


import com.example.emtechelppathbackend.dtos.userProfile.SocialMediaDto;
import com.example.emtechelppathbackend.entities.userProfile.SocialMedia;

import com.example.emtechelppathbackend.exceptions.UserDetailsNotFoundException;

import com.example.emtechelppathbackend.repositories.SocialMediaRepository;
import com.example.emtechelppathbackend.security.entities.Users;
import com.example.emtechelppathbackend.security.repositories.UserRepository;
import com.example.emtechelppathbackend.services.SocialMediaService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SocialMediaServiceImpl implements SocialMediaService {
    private final UserRepository userRepository;
    private final SocialMediaRepository socialMediaRepository;

    public SocialMediaServiceImpl(UserRepository userRepository, SocialMediaRepository socialMediaRepository) {
        this.userRepository = userRepository;
        this.socialMediaRepository = socialMediaRepository;
    }

    @Override
    public SocialMediaDto addSocialMediaLink(SocialMediaDto socialMediaDto, Long userId) {
        SocialMedia socialMedia = mapToEntity(socialMediaDto);
        Users users = userRepository.findById(userId).orElseThrow(()->new UserDetailsNotFoundException("user not found"));
        if (users.getSocialMedia() == null) {
            socialMedia.setUser(users);
            socialMedia.setId(socialMediaDto.getId());
            socialMedia.setFacebook(socialMediaDto.getFacebook());
            socialMedia.setLinkedIn(socialMediaDto.getLinkedIn());
            socialMedia.setGithub(socialMediaDto.getGithub());
            socialMedia.setTwitter(socialMediaDto.getTwitter());
            socialMedia.setInstagram(socialMediaDto.getInstagram());
            SocialMedia newSocial = socialMediaRepository.save(socialMedia);
            return mapToDto(newSocial);
        }
        throw new UserDetailsNotFoundException("you social medias are set you can update them");
    }

    @Override
    public List<SocialMediaDto> viewSocialMediaByUserId(Long userId) {
        List<SocialMedia> socialMedia = socialMediaRepository.findByUserId(userId);
        return socialMedia.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public SocialMediaDto updateSocialMedia( Long userId, SocialMediaDto socialMediaDto) {
        SocialMedia socialMedia = mapToEntity(socialMediaDto);
        Users users = userRepository.findById(userId).orElseThrow(()->new UserDetailsNotFoundException("user not found"));
       //SocialMedia socialMedia = socialMediaRepository.findById(socialId).orElseThrow(()->new UserDetailsNotFoundException("this socials does not exist"));
//        if (!Objects.equals(socialMedia.getUser().getId(), users.getId())){
//           throw new UserDetailsNotFoundException("social media do not belong to this user");
//        }
        if (users.getId() != null) {
            socialMedia.setLinkedIn(socialMediaDto.getLinkedIn());
            socialMedia.setGithub(socialMediaDto.getGithub());
            socialMedia.setFacebook(socialMediaDto.getFacebook());
            socialMedia.setTwitter(socialMediaDto.getTwitter());
            socialMedia.setInstagram(socialMediaDto.getInstagram());
            SocialMedia updateSocials = socialMediaRepository.save(socialMedia);
            return mapToDto(updateSocials);
        }
        throw new UserDetailsNotFoundException("user do not belong to this user");
    }

    @Override
    public SocialMediaDto updateSocialMediaByUserId(Long userId, SocialMediaDto socialMediaDto) {
        Users users = userRepository.findById(userId).orElseThrow(()->new UserDetailsNotFoundException("user not found"));
        SocialMedia socialMedia = users.getSocialMedia();
        if (socialMedia == null){
            throw  new UserDetailsNotFoundException("Social Media not found for the user");
        }
        socialMedia.setLinkedIn(socialMediaDto.getLinkedIn());
        socialMedia.setGithub(socialMediaDto.getGithub());
        socialMedia.setFacebook(socialMediaDto.getFacebook());
        socialMedia.setInstagram(socialMediaDto.getInstagram());
        return mapToDto(socialMedia);
    }

    @Override
    public void deleteSocialMedia(Long socialId, Long userId) {
        Users users = userRepository.findById(userId).orElseThrow(()->new UserDetailsNotFoundException("user not found"));
        SocialMedia socialMedia = socialMediaRepository.findById(socialId).orElseThrow(()->new UsernameNotFoundException("this socials does not exist"));
        if (!Objects.equals(socialMedia.getUser().getId(), users.getId())){
            throw new UserDetailsNotFoundException("social media do not belong to this user");
        }
        socialMediaRepository.delete(socialMedia);
    }
    private SocialMediaDto mapToDto(SocialMedia socialMedia){
        SocialMediaDto socialMediaDto = new SocialMediaDto();
        socialMediaDto.setId(socialMedia.getId());
        socialMediaDto.setFacebook(socialMedia.getFacebook());
        socialMediaDto.setTwitter(socialMedia.getTwitter());
        socialMediaDto.setLinkedIn(socialMedia.getLinkedIn());
        socialMediaDto.setInstagram(socialMedia.getInstagram());

        socialMediaDto.setGithub(socialMedia.getGithub());
        return socialMediaDto;
    }
    private SocialMedia mapToEntity(SocialMediaDto socialMediaDto){
        SocialMedia socialMedia = new SocialMedia();
        socialMedia.setId(socialMediaDto.getId());
        socialMedia.setFacebook(socialMediaDto.getFacebook());
        socialMedia.setTwitter(socialMediaDto.getTwitter());
        socialMedia.setLinkedIn(socialMediaDto.getLinkedIn());
        socialMedia.setGithub(socialMediaDto.getGithub());

        socialMedia.setInstagram(socialMediaDto.getInstagram());

        return socialMedia;
    }
}

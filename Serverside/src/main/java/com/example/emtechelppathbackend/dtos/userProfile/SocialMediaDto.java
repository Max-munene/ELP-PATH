package com.example.emtechelppathbackend.dtos.userProfile;

import lombok.Data;

@Data
public class SocialMediaDto {
    private Long id;
    private String linkedIn;
    private String twitter;
    private String facebook;
    private String github;
    private String instagram;

}

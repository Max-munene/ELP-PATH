package com.example.emtechelppathbackend.dtos.userProfile;

import lombok.Data;

import java.util.List;

@Data
public class ProfileDto {
    private Long id;
    private String email;
    private String title;
    private String website;
    private Long phoneNo;
}

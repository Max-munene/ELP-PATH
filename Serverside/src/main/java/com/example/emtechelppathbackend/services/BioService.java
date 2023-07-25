package com.example.emtechelppathbackend.services;


import com.example.emtechelppathbackend.dtos.userProfile.BioDto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BioService{
BioDto addBio(Long userId,BioDto bioDto);
List<BioDto> viewBioByUserId(Long userId);
BioDto updateBioByUserId(Long userId,Long bioId,BioDto bioDto);
void deleteBio(Long userId,Long bioId);
}

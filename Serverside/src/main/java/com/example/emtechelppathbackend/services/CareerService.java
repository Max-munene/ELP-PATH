package com.example.emtechelppathbackend.services;


import com.example.emtechelppathbackend.dtos.userProfile.CareerDto;

import java.util.List;

public interface CareerService {
    CareerDto addSkills(CareerDto careerDto, Long userId);
    List<CareerDto> viewCareerByUserId(Long userId);
    CareerDto updateCareer(Long userId,Long careerId,CareerDto careerDto);
    void deleteCareer(Long userId,Long careerId);
}

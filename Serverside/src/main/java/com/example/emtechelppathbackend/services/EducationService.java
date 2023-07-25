package com.example.emtechelppathbackend.services;


import com.example.emtechelppathbackend.dtos.userProfile.EducationDto;
import com.example.emtechelppathbackend.entities.userProfile.Education;
import org.hibernate.sql.Update;


import java.util.List;

public interface EducationService {
    EducationDto addEducation(EducationDto educationDto,Long userId);
    List<EducationDto> getEducationByUserId(Long userId);
    Education updateEducation(Long userId, Long educationId, Education education);
    EducationDto updateUserEducation(Long userId,Long educationId,EducationDto educationDto);
    void deleteEducation(Long userId,Long educationId);

}

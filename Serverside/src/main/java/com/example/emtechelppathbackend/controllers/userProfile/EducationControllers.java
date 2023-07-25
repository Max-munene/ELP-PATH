package com.example.emtechelppathbackend.controllers.userProfile;

import com.example.emtechelppathbackend.dtos.userProfile.EducationDto;
import com.example.emtechelppathbackend.entities.userProfile.Education;
import com.example.emtechelppathbackend.services.EducationService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/education")
public class EducationControllers {
    private final EducationService educationService;
    private final ModelMapper modelMapper;

    public EducationControllers(EducationService educationService, ModelMapper modelMapper) {
        this.educationService = educationService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/{userId}/create")
    public ResponseEntity<EducationDto>createEducation(@Valid @PathVariable(value = "userId")Long userId, @RequestBody EducationDto educationDto){
        return new ResponseEntity<>(educationService.addEducation(educationDto,userId), HttpStatus.CREATED);
    }
    @GetMapping("/{userId}/view")
    public List<EducationDto> getEducation(@PathVariable(value = "userId")Long userId){
        return educationService.getEducationByUserId(userId);
    }
    @PutMapping("/user/{userId}/{educationId}/update")
    public ResponseEntity<EducationDto>updateEducation(@Valid @PathVariable(value = "userId")Long userId, @PathVariable(value = "educationId")Long educationId, EducationDto educationDto){
        EducationDto updatedEducation = educationService.updateUserEducation(userId, educationId, educationDto);
        return new ResponseEntity<>(updatedEducation,HttpStatus.OK);
    }
    @PutMapping("/{userId}/{educationId}/update")
    public ResponseEntity<EducationDto> updateUserEducation(@PathVariable(value = "userId")Long userId, @PathVariable(value = "educationId")Long educationId, EducationDto educationDto){
        Education education = modelMapper.map(educationDto, Education.class);
        Education newEducation = educationService.updateEducation(userId,educationId,education);
        EducationDto educationResponse = modelMapper.map(newEducation, EducationDto.class);
        return ResponseEntity.ok().body(educationResponse);
    }
    @DeleteMapping("/{userId}/{educationId}/delete")
    public ResponseEntity<?>deleteEducation(@PathVariable(value = "userId")Long userId,@PathVariable(value = "educationId")Long educationId){
        educationService.deleteEducation(userId, educationId);
        return new ResponseEntity<>("education deleted",HttpStatus.OK);
    }

}

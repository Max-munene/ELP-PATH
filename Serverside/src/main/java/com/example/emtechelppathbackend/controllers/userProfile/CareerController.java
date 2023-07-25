package com.example.emtechelppathbackend.controllers.userProfile;

import com.example.emtechelppathbackend.dtos.userProfile.CareerDto;
import com.example.emtechelppathbackend.services.CareerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/career")
public class CareerController {
    private final CareerService careerService;

    public CareerController(CareerService careerService) {
        this.careerService = careerService;
    }
    @PostMapping("/{userId}/create")
    public ResponseEntity<CareerDto> addSkills(@PathVariable(value = "userId")Long userId, @RequestBody CareerDto careerDto){
        return new ResponseEntity<>(careerService.addSkills(careerDto, userId), HttpStatus.CREATED);
    }
    @GetMapping("/{userId}/view")
    public List<CareerDto> viewCareerByUserId(@PathVariable(value = "userId")Long userId){
        return careerService.viewCareerByUserId(userId);
    }
    @PutMapping("/user/{userId}/{careerId}/update")
    public ResponseEntity<CareerDto> updateCareer(@PathVariable(value = "userId")Long userId,@PathVariable(value = "careerId")Long careerId,@RequestBody CareerDto careerDto){
        CareerDto updatedCareer = careerService.updateCareer(userId, careerId, careerDto);
        return new ResponseEntity<>(updatedCareer,HttpStatus.OK);
    }
    @DeleteMapping("/{userId}/{careerId}/delete")
    public ResponseEntity<?>deleteCareer(@PathVariable(value = "userId")Long userId,@PathVariable(value = "careerId")Long careerId){
        careerService.deleteCareer(userId, careerId);
        return new ResponseEntity<>("career deleted successfully",HttpStatus.OK);
    }
}

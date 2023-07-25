package com.example.emtechelppathbackend.branchchampion.controllers;

import com.example.emtechelppathbackend.customizedimports.ApiResponse;
import com.example.emtechelppathbackend.branchchampion.dtos.ApplicationDto;
import com.example.emtechelppathbackend.branchchampion.entities.Application;
import com.example.emtechelppathbackend.branchchampion.services.scholarService.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/applications")

public class ApplicationsController {

    private final ApplicationService applicationService;

    private final ModelMapper modelMapper;


    @GetMapping("/display-applications")
    public List<ApplicationDto> displayApplications() {
        return applicationService.displayApplications()
                .stream().map(application -> modelMapper.map(application, ApplicationDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/display-applications/{id}")
    public ResponseEntity<ApplicationDto> displayApplicationDetails(@PathVariable(name = "id") Long id) {
        Application application = applicationService.displayApplicationDetailsById(id);

        //convert to DTO
        ApplicationDto applicationResponse = modelMapper.map(application, ApplicationDto.class);

        return ResponseEntity.ok().body(applicationResponse);
    }

    @GetMapping("/count-all-applications")
    public long countTotalApplicants(){
        return applicationService.getTotalApplications();
    }
    @GetMapping("/count-awarded-applications")
    public long countTotalAwardedApplicants(){
        return applicationService.getAwardedApplications();
    }

    @PostMapping("/add-new-application")
    public ResponseEntity<ApplicationDto> addNewApplication(@RequestBody ApplicationDto applicationDto) {

        //convert Dto to entity
        Application applicationRequest = modelMapper.map(applicationDto, Application.class);

        Application application = applicationService.addNewApplication(applicationRequest);

        //convert entity to DTO
        ApplicationDto applicationResponse = modelMapper.map(application, ApplicationDto.class);

        return new ResponseEntity<ApplicationDto>(applicationResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update-application/{id}")
    public ResponseEntity<ApplicationDto> updateApplication(@PathVariable long id,
            @RequestBody ApplicationDto applicationDto) {

        //convert DTO to entity
        Application applicationRequest = modelMapper.map(applicationDto, Application.class);

        Optional<Application> application = applicationService.updateApplicationById(id, applicationRequest);

        //entity to DTO
        ApplicationDto applicationResponse = modelMapper.map(application, ApplicationDto.class);
       return ResponseEntity.ok().body(applicationResponse);
    }


    @DeleteMapping("/delete-application/{id}")
    public ResponseEntity<ApiResponse> deleteApplication(@PathVariable(name = "id") Long id) {
        applicationService.deleteApplicationById(id);
        ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, "Application deleted successfully", HttpStatus.OK);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}



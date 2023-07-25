/*
package com.example.emtechelppathbackend.branchchampion.controllers;

import com.example.emtechelppathbackend.branchchampion.entities.Interview;
import com.example.emtechelppathbackend.branchchampion.services.serviceimpl.InterviewServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/interviews")
public class InterviewController {

    private final InterviewServiceImpl interviewServiceImpl;

    @PostMapping("/add-new-interview")
    public void addNewInterview(@RequestBody Interview interview){
        interviewServiceImpl.addNewInterview();
    }

    @PutMapping (value = "/update-interview/{id}")
    public void updateInterview(@PathVariable String id){
        interviewServiceImpl.updateInterview();
    }

    @GetMapping("/display-interviews")
    public List<Interview> displayInterviews(){
        return interviewServiceImpl.displayInterviews();
    }

    @GetMapping ("/display-interview/{id}")
    public Interview displayInterviewDetails(@PathVariable String id){
        return interviewServiceImpl.displayInterviewDetails();
    }

    @DeleteMapping("/delete-interview/{id}")
    public void deleteInterview(@PathVariable String id){
        interviewServiceImpl.deleteInterview();
    }

}
*/

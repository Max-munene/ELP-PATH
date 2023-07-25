package com.example.emtechelppathbackend.branchchampion.controllers;

import com.example.emtechelppathbackend.branchchampion.entities.scholars.School;
import com.example.emtechelppathbackend.branchchampion.services.scholarService.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/schools")
public class SchoolController {

    private final SchoolService schoolService;

    @GetMapping("/all")
    public ResponseEntity<List<School>> displayAllSchools(){
        List<School> schools = schoolService.displayAllSchools();
        return new ResponseEntity<>(schools, HttpStatus.OK);
    }
    @GetMapping("/display/{id}")
    public ResponseEntity<School> displaySchoolById(@PathVariable Long id){
        Optional<School> school = schoolService.displaySchoolById(id);
        if (school.isPresent()){
            return new ResponseEntity<>(school.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add-new-school")
    public ResponseEntity<School> addNewSchool(@RequestBody School school){
        School addedSchool = schoolService.addNewSchool(school);
        return new ResponseEntity<>(addedSchool, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<School> updateSchoolById(@PathVariable Long id, @RequestBody School updatedSchool){
        School updated = schoolService.updateSchoolById(id, updatedSchool);
        if (updated != null){
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSchoolById(@PathVariable Long id){
        boolean deleted = schoolService.deleteSchoolById(id);
        if (deleted){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

package com.example.emtechelppathbackend.branchchampion.dtos.scholars;

import com.example.emtechelppathbackend.branchchampion.entities.Application;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;
@Data
public class ScholarEducationDto {
    private Long id;
    private Long form;
    private Long term;
    private String schoolName;
    private LocalDate opening_date;
    private String openingGrade;
    private String midTermGrade;
    private String closingGrade;
    @JsonIgnoreProperties("hibernateLazyInitializer")
    private Application application;
}

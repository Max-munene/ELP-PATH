package com.example.emtechelppathbackend.branchchampion.dtos;

import com.example.emtechelppathbackend.branchchampion.entities.ApplicationSchoolMapping;
import com.example.emtechelppathbackend.branchchampion.entities.InterviewReportPDF;
import com.example.emtechelppathbackend.branchchampion.entities.ApplicationStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class ApplicationDto {
    private long id;
    private String applicantFirstName;
    private String applicantLastName;
    private String branch;
    private LocalDate dateOfApplication;
    private LocalDate dateOfAwarding;
    private LocalDate dateOfInterview;
    private ApplicationStatus applicationStatus;
    private Set<ApplicationSchoolMapping> applicationSchoolMappings;
//    private InterviewReportPDF interviewReportPDF;
}

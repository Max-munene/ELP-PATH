package com.example.emtechelppathbackend.dtos.userProfile;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EducationDto {
    private Long id;
    private String school_name;
    private String course;
    private String grade;
    @NotNull(message = "start Date should not be null")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate start_date;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(nullable = true)
    private LocalDate  end_date;
}

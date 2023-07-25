package com.example.emtechelppathbackend.branchchampion.entities;

import com.example.emtechelppathbackend.branchchampion.entities.scholars.School;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application_school_mapping")
public class ApplicationSchoolMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate; // Start date when the applicant joined the school

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate; // End date when the applicant left the school


    @ManyToOne
    @JoinColumn(name = "application_id")
    private Application application;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;
}
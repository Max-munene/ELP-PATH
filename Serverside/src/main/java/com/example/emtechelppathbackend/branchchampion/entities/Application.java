package com.example.emtechelppathbackend.branchchampion.entities;

import com.example.emtechelppathbackend.branchchampion.entities.scholars.Branch;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String applicantFirstName;

    private String applicantLastName;

    private ApplicationStatus applicationStatus;

   @NotNull(message = "You must include the date the application was made")
   @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfApplication;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfInterview;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfAwarding;

    @OneToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @OneToMany(mappedBy = "application")
    private Set<ApplicationSchoolMapping> applicationSchoolMappings;

}

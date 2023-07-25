package com.example.emtechelppathbackend.branchchampion.entities.scholars;

import com.example.emtechelppathbackend.branchchampion.entities.Application;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "education_progress")
public class ScholarEducation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Long form;
    private Long term;
    //   Why not use the school field in entity Application to fetch the school name?
//    private String schoolName;

    @NotNull(message = "opening Date should not be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate opening_date;

    private String openingGrade;

    private String midTermGrade;

    private String closingGrade;

    /*
        @ManyToOne(fetch = FetchType.LAZY)
        @JsonIgnore
        @JoinColumn(name = "scholar_id")
        private Scholar scholar;
    */
    @NotNull(message = "Each education entry should be associated to a scholar")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "application_id")
    private Application application;

}

package com.example.emtechelppathbackend.entities.userProfile;


import com.example.emtechelppathbackend.branchchampion.entities.Application;
import com.example.emtechelppathbackend.security.entities.Users;
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
@Table(name = "education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String school_name;
    private String course;
    private String grade;

    @NotNull(message = "start Date should not be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate start_date;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = true)
    private LocalDate end_date;

    //a user might have attended many schools
    @JoinColumn(name = "users_id")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Users user;

}

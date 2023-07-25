/*
package com.example.emtechelppathbackend.entities;

import com.example.emtechelppathbackend.branchchampion.entities.scholars.ScholarEducation;
import com.example.emtechelppathbackend.branchchampion.entities.scholars.ScholarExpenses;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "scholars")
public class Scholar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Scholar Number", nullable = false)
    private Long scholarNumber;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Status", nullable = false )
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "Year of Application", nullable = false)
    private String yearOfApplication;

    @Column(name = "Branch", nullable = false)
    private String branch;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "scholar")
    private List<ScholarEducation> scholarEducation = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "scholar")
    private List<ScholarExpenses> scholarExpenses = new ArrayList<>();

   // @OneToOne(mappedBy = "scholar")
    //private ScholarApplication application;
}*/

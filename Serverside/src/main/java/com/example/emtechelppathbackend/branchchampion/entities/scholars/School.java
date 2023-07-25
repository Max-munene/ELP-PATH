package com.example.emtechelppathbackend.branchchampion.entities.scholars;

import com.example.emtechelppathbackend.branchchampion.entities.ApplicationSchoolMapping;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "branch_details")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String schoolName;

    private String additionalInformation;

    @OneToMany(mappedBy = "school")
    private Set<ApplicationSchoolMapping> applicationSchoolMappings;
}

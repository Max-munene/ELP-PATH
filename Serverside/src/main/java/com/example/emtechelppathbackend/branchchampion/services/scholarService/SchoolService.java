package com.example.emtechelppathbackend.branchchampion.services.scholarService;

import com.example.emtechelppathbackend.branchchampion.entities.scholars.School;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface SchoolService {
    List<School> displayAllSchools();

    Optional<School> displaySchoolById(Long id);

    School addNewSchool(School school);

    School updateSchoolById(Long id, School updatedSchool);

    boolean deleteSchoolById(Long id);
}

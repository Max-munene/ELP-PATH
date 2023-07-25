package com.example.emtechelppathbackend.branchchampion.services.serviceimpl;

import com.example.emtechelppathbackend.branchchampion.entities.scholars.School;
import com.example.emtechelppathbackend.branchchampion.repositories.SchoolRepository;
import com.example.emtechelppathbackend.branchchampion.services.scholarService.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;

    public List<School> displayAllSchools() {
        return schoolRepository.findAll();
    }

    public Optional<School> displaySchoolById(Long id) {
        return schoolRepository.findById(id);
    }

    public School addNewSchool(School school) {
        return schoolRepository.save(school);
    }


    public School updateSchoolById(Long id, School updatedSchool) {
        Optional<School> optionalSchool = schoolRepository.findById(id);
        if (optionalSchool.isPresent()) {
            School school = optionalSchool.get();
            school.setSchoolName(updatedSchool.getSchoolName());
            school.setAdditionalInformation(updatedSchool.getAdditionalInformation());
            return schoolRepository.save(school);
        }
        return null; // todo or throw an exception indicating the school doesn't exist
    }

    public boolean deleteSchoolById(Long id) {
        if (schoolRepository.existsById(id)) {
            schoolRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

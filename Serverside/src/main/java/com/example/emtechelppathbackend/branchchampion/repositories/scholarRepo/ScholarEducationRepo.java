package com.example.emtechelppathbackend.branchchampion.repositories.scholarRepo;

import com.example.emtechelppathbackend.branchchampion.entities.scholars.ScholarEducation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface ScholarEducationRepo extends JpaRepository<ScholarEducation, Long> {
    List<ScholarEducation> findByApplicationId(Long applicationId);
}

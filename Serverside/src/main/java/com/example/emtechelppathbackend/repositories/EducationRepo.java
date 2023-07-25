package com.example.emtechelppathbackend.repositories;
import com.example.emtechelppathbackend.entities.userProfile.Education;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//import com.example.emtechelppathbackend.entities.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EducationRepo extends JpaRepository<Education,Long> {
//    @Query("SELECT education FROM Education education WHERE education.profile_id=:profileId")

List<Education> findByUserId(Long userId);

}

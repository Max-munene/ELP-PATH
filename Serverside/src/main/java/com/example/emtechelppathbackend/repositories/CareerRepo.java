package com.example.emtechelppathbackend.repositories;

import com.example.emtechelppathbackend.entities.userProfile.Career;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CareerRepo extends JpaRepository<Career,Long> {
        List<Career> findByUserId(Long userId);
}

package com.example.emtechelppathbackend.repositories;


import com.example.emtechelppathbackend.entities.userProfile.Bio;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BioRepository extends JpaRepository<Bio,Long> {
    List<Bio> findByUserId(Long userId);
}

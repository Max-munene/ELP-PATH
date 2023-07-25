package com.example.emtechelppathbackend.repositories;


import com.example.emtechelppathbackend.entities.userProfile.Image;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByUserId(Long userId);
}

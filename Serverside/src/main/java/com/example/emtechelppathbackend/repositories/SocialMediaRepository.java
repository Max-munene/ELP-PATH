package com.example.emtechelppathbackend.repositories;


import com.example.emtechelppathbackend.entities.userProfile.SocialMedia;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocialMediaRepository extends JpaRepository<SocialMedia,Long> {
    List<SocialMedia> findByUserId(Long userId);

}

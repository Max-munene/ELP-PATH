package com.example.emtechelppathbackend.repositories;


import com.example.emtechelppathbackend.entities.userProfile.Hub;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HubRepo extends JpaRepository<Hub,Long> {
    List<Hub> findByUserId(Long userId);


}

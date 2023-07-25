package com.example.emtechelppathbackend.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.emtechelppathbackend.entities.userProfile.Profile;


@Repository
public interface ProfileRepo extends JpaRepository<Profile,Long> {
  //  @Query("SELECT user FROM profile WHERE profile.user_id=:userId")
  List<Profile> findProfileByUserId(Long userId);
}

package com.example.emtechelppathbackend.security.repositories;

import com.example.emtechelppathbackend.security.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <Users, Long>{

     Optional<Users>findById(Long userId);
      Optional<Users> findByUserEmail(String userEmail);
      boolean existsByUserEmail(String userEmail);

}

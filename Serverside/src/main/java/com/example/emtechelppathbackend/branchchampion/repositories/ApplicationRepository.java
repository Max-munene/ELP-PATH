package com.example.emtechelppathbackend.branchchampion.repositories;

import com.example.emtechelppathbackend.branchchampion.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}

package com.example.emtechelppathbackend.branchchampion.repositories;

import com.example.emtechelppathbackend.branchchampion.entities.scholars.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
}

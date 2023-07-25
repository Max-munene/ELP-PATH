/*
package com.example.emtechelppathbackend.repositories;

import com.example.emtechelppathbackend.entities.Scholar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScholarsRepository extends JpaRepository<Scholar,Long> {
   // List <Scholars> findByScholarsId(Long scholarsId);
    List <Scholar> findScholarByName(String name);

    List <Scholar> findScholarByStatus(String status);

    List <Scholar> findScholarByBranch(String branch);

    List <Scholar> findScholarByYearOfApplication(String year);

}
*/

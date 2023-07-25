package com.example.emtechelppathbackend.repositories;

import com.example.emtechelppathbackend.entities.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsRepo extends JpaRepository<Events, Long> {

}

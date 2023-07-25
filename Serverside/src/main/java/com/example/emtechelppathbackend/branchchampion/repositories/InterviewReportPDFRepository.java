package com.example.emtechelppathbackend.branchchampion.repositories;

import com.example.emtechelppathbackend.branchchampion.entities.InterviewReportPDF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewReportPDFRepository extends JpaRepository<InterviewReportPDF, Long> {
}

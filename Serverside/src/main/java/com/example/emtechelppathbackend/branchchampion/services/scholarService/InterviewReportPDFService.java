package com.example.emtechelppathbackend.branchchampion.services.scholarService;

import com.example.emtechelppathbackend.branchchampion.entities.InterviewReportPDF;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface InterviewReportPDFService {

     InterviewReportPDF updateInterviewReportPDFById(Long id, byte[] pdf);

     List<InterviewReportPDF> displayInterviewReportPDFs();

     Optional<InterviewReportPDF> displayInterviewReportPDFDetailsById(Long id);

     InterviewReportPDF addNewInterviewReportPDF(InterviewReportPDF InterviewReportPDF);

     void deleteInterviewReportPDFById(Long id);
}

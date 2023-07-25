package com.example.emtechelppathbackend.branchchampion.services.serviceimpl;

import com.example.emtechelppathbackend.branchchampion.entities.InterviewReportPDF;
import com.example.emtechelppathbackend.branchchampion.repositories.InterviewReportPDFRepository;
import com.example.emtechelppathbackend.branchchampion.services.scholarService.InterviewReportPDFService;
import com.example.emtechelppathbackend.customexceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class InterviewReportPDFServiceImpl implements InterviewReportPDFService {

    private final InterviewReportPDFRepository InterviewReportPDFRepository;

    @Override
    public InterviewReportPDF updateInterviewReportPDFById(Long id, byte[] pdf) {
        Optional<InterviewReportPDF> optionalInterviewReportPDF = InterviewReportPDFRepository.findById(id);
        if (optionalInterviewReportPDF.isPresent()){
            InterviewReportPDF victim = optionalInterviewReportPDF.get();
            victim.setPdfData(pdf);
            return InterviewReportPDFRepository.save(victim);
        }
        throw new IllegalArgumentException("InterviewReport PDF not found with id:" + id);
    }


    //return all InterviewReports present in the database
    @Override
    public List<InterviewReportPDF> displayInterviewReportPDFs() {
        return InterviewReportPDFRepository.findAll();
    }

    @Override
    public Optional<InterviewReportPDF> displayInterviewReportPDFDetailsById(Long id) {
      return InterviewReportPDFRepository.findById(id);
    }

    @Override
    public InterviewReportPDF addNewInterviewReportPDF(InterviewReportPDF InterviewReportPDF) {
        return InterviewReportPDFRepository.save(InterviewReportPDF);
    }

    @Override
    public void deleteInterviewReportPDFById(Long id) {
        InterviewReportPDF InterviewReportPDF = InterviewReportPDFRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("InterviewReportPDF", "id", id));
        InterviewReportPDFRepository.delete(InterviewReportPDF);
    }
}

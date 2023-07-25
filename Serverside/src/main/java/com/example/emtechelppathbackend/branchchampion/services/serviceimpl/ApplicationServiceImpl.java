package com.example.emtechelppathbackend.branchchampion.services.serviceimpl;

import com.example.emtechelppathbackend.branchchampion.entities.Application;
import com.example.emtechelppathbackend.branchchampion.entities.ApplicationStatus;
import com.example.emtechelppathbackend.branchchampion.repositories.InterviewReportPDFRepository;
import com.example.emtechelppathbackend.branchchampion.repositories.ApplicationRepository;
import com.example.emtechelppathbackend.branchchampion.services.scholarService.ApplicationService;
import com.example.emtechelppathbackend.customexceptions.ResourceNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.sql.Select;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final InterviewReportPDFRepository InterviewReportPDFRepository;
    private final EntityManager entityManager;


    @Override
    public Application addNewApplication(Application application) {
       /*
       //todo if associated with pdf in a one to one relation

          if (application.getInterviewReportPDF() != null) {
            Optional<InterviewReportPDF> interviewReportPDFOptional = InterviewReportPDFRepository.findById(application.getInterviewReportPDF().getId());
            if (interviewReportPDFOptional.isPresent()) {
                application.setInterviewReportPDF(interviewReportPDFOptional.get());
            } else {
                throw new IllegalArgumentException("PDF not found");
            }
        }*/
        return applicationRepository.save(application);
    }

    @Override
    public Optional<Application> updateApplicationById(Long id, Application applicationRequest) {
        Optional<Application> optionalApplication = applicationRepository.findById(id);
        if (optionalApplication.isPresent()) {
            Application existingApplication = optionalApplication.get();
            existingApplication.setApplicantFirstName(applicationRequest.getApplicantFirstName());
            existingApplication.setApplicantLastName(applicationRequest.getApplicantLastName());
            existingApplication.setApplicationStatus(applicationRequest.getApplicationStatus());
            existingApplication.setDateOfApplication(applicationRequest.getDateOfApplication());
            existingApplication.setBranch(applicationRequest.getBranch());
            existingApplication.setDateOfAwarding(applicationRequest.getDateOfAwarding());

         /* // todo this commented out section is applicable when there's a field of pdf in the application entity which is a foreign key associated with a file
           InterviewReportPDF requestedPDF = applicationRequest.getInterviewReportPDF();
            if (requestedPDF != null) {
                InterviewReportPDF existingPDF = existingApplication.getInterviewReportPDF();

                if (existingPDF != null) {
                    byte[] pdfData = requestedPDF.getPdfData();
                    String InterviewReportPDFName = requestedPDF.getInterviewReportPDFName();

                    // Update only if requested PDF data is provided
                    if (pdfData != null && InterviewReportPDFName != null) {
                        // Update the existing InterviewReportPDF entity
                        existingPDF.setPdfData(pdfData);
                        existingPDF.setInterviewReportPDFName(InterviewReportPDFName);
                        // Update other properties of InterviewReportPDF if needed

                        // Save the updated InterviewReportPDF
                        InterviewReportPDFRepository.save(existingPDF);
                    }
                } else {
                    // Create a new InterviewReportPDF entity if it doesn't exist
                    InterviewReportPDF newPDF = new InterviewReportPDF();
                    newPDF.setPdfData(requestedPDF.getPdfData());
                   newPDF.setInterviewReportPDFName(requestedPDF.getInterviewReportPDFName());
                    // Set other properties of newPDF if needed

                    // Save the new InterviewReportPDF
                    InterviewReportPDFRepository.save(newPDF);

                    // Associate the new InterviewReportPDF with the Application entity
                    existingApplication.setInterviewReportPDF(newPDF);
                }
            }*/

            // Save the updated Application entity
            applicationRepository.save(existingApplication);

            return Optional.of(existingApplication);
        } else {
            // Handle the case when Application is not present
            // Return an empty Optional, throw an exception, or handle it in another appropriate way
            return Optional.empty();
        }
    }



    @Override
    public void deleteApplicationById(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Application","id",id));
        applicationRepository.delete(application);
    }

    @Override
    public List<Application> displayApplications() {
        return applicationRepository.findAll();
    }

    @Override
    public Application displayApplicationDetailsById(Long id) {
        Optional<Application> result = applicationRepository.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        else {
            throw new ResourceNotFoundException("Application", "id", id);
        }
    }

    // Method to return the total number of applications@Override
    @Override
    public long getTotalApplications() {
        String queryString = "SELECT COUNT(a) FROM Application a";
        TypedQuery<Long> query = entityManager.createQuery(queryString, Long.class);
        return query.getSingleResult();
    }

    // Method to return the number of applications with status "AWARDED"
    @Override
    public long getAwardedApplications() {
        String queryString = "SELECT COUNT (a) FROM Application a WHERE a.applicationStatus = :status";
        TypedQuery<Long> query = entityManager.createQuery(queryString, Long.class);
        query.setParameter("status", ApplicationStatus.AWARDED);
        return query.getSingleResult();
    }

}

package com.example.emtechelppathbackend.branchchampion.controllers;

import com.example.emtechelppathbackend.branchchampion.entities.InterviewReportPDF;
import com.example.emtechelppathbackend.branchchampion.services.scholarService.InterviewReportPDFService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/application-pdf")
@RequiredArgsConstructor
public class InterviewReportPDFController {

    private final InterviewReportPDFService InterviewReportPDFService;


    @GetMapping("/display-all-pdfs")
    public ResponseEntity<List<InterviewReportPDF>> displayInterviewReportPDF() {
        List<InterviewReportPDF> pdfs = InterviewReportPDFService.displayInterviewReportPDFs();
        return ResponseEntity.ok(pdfs);
    }

    //displaying pdf by id(This endpoint is designed to retrieve an entity by its ID, i.e. its id, name and pdf.)
    @GetMapping("/display-pdf/{id}")
    public ResponseEntity<InterviewReportPDF> displayInterviewReportPDFById(@PathVariable Long id) {
        Optional<InterviewReportPDF> optionalInterviewReportPDF = InterviewReportPDFService.displayInterviewReportPDFDetailsById(id);
        return optionalInterviewReportPDF.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    //displaying actual pdf (This endpoint is designed to retrieve the PDF file associated with the application)
    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> displayInterviewReportPDFDetailsById(@PathVariable Long id) {
        Optional<InterviewReportPDF> optionalInterviewReportPDF = InterviewReportPDFService.displayInterviewReportPDFDetailsById(id);
        if (optionalInterviewReportPDF.isPresent()) {
            InterviewReportPDF pdf = optionalInterviewReportPDF.get();
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(pdf.getPdfData());
        }
        return ResponseEntity.notFound().build();
    }

    //creating a pdf(to be attached to application)
    @PostMapping(value = "/add-new-InterviewReportPDF", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<InterviewReportPDF> addNewInterviewReportPDF
    (@RequestParam("name") String name, @RequestParam("pdfFile") MultipartFile pdfFile)
            throws IOException {
        InterviewReportPDF InterviewReportPDF = new InterviewReportPDF();
//        InterviewReportPDF.setInterviewReportPDFName(name);
        InterviewReportPDF.setPdfData(pdfFile.getBytes());
        InterviewReportPDF savedInterviewReportPDF = InterviewReportPDFService.addNewInterviewReportPDF(InterviewReportPDF);
        return ResponseEntity.ok(savedInterviewReportPDF);
    }

    //updating application's pdf
    @PutMapping("update-pdf/{id}")
    public ResponseEntity<InterviewReportPDF> updateInterviewReportPDFById
    (@PathVariable Long id, @RequestParam("pdfFile") MultipartFile pdfFile) throws IOException {
        byte[] pdfData = pdfFile.getBytes();
        InterviewReportPDF updatedInterviewReportPDF = InterviewReportPDFService.updateInterviewReportPDFById(id, pdfData);
        return ResponseEntity.ok(updatedInterviewReportPDF);
    }

    //deleting an application's pdf
    @DeleteMapping("/delete-pdf/{id}")
    public ResponseEntity<String> deleteInterviewReportPDFById(@PathVariable Long id) {
        InterviewReportPDFService.deleteInterviewReportPDFById(id);
        return ResponseEntity.ok("InterviewReportPDF deleted successfully.");
    }
}

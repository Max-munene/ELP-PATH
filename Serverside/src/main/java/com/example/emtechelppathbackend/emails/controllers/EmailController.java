package com.example.emtechelppathbackend.emails.controllers;

import com.example.emtechelppathbackend.emails.entities.EmailDetails;
import com.example.emtechelppathbackend.emails.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/send-email")
    @PreAuthorize("hasAuthority('alumni:read')")
    public String sendMail(@RequestBody EmailDetails emailDetails){

        return emailService.sendWithOutAttachment(emailDetails);
    }
    @PostMapping("/send-attached-email")
    public String sendAttachedMail(@RequestBody EmailDetails emailDetails){
        return  emailService.sendMailWithAttachment(emailDetails);
    }

}

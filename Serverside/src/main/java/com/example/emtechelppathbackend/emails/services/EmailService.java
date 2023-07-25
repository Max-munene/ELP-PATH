package com.example.emtechelppathbackend.emails.services;

import com.example.emtechelppathbackend.emails.entities.EmailDetails;

public interface EmailService {
    String sendWithOutAttachment(EmailDetails emailDetails);

    String sendMailWithAttachment(EmailDetails emailDetails);
}

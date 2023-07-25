package com.example.emtechelppathbackend.emails.services;

import com.example.emtechelppathbackend.emails.entities.EmailDetails;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailSender;

    @Override
    public String sendWithOutAttachment(EmailDetails emailDetails) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            //setting up necessary details
            simpleMailMessage.setFrom(emailSender);
            simpleMailMessage.setTo(emailDetails.getRecipient());
            simpleMailMessage.setText(emailDetails.getMessageBody());
            simpleMailMessage.setSubject(emailDetails.getSubject());

            //sending the mail
            javaMailSender.send(simpleMailMessage);
            return "Mail sent Successfully";
        } catch (Exception e) {
            return "Error when sending mail";
        }
    }
    //sending mail with attachment

    @Override
    public String sendMailWithAttachment(EmailDetails emailDetails) {
        //creating a mimemessage

        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            //setting multipart as true for attachment to be sent
            mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(emailSender);
            mimeMessageHelper.setTo(emailDetails.getRecipient());
            mimeMessageHelper.setText(emailDetails.getMessageBody());
            mimeMessageHelper.setSubject(emailDetails.getSubject());

            //adding the attachment
            FileSystemResource emailFile = new FileSystemResource(new File(emailDetails.getAttachment()));

            mimeMessageHelper.addAttachment(Objects.requireNonNull(emailFile.getFilename()), emailFile);

            //sending the mail
            javaMailSender.send(mimeMailMessage);
            return "attached mail sent successfully";
        } catch (Exception e) {
            return "Error when sending mail with attachment";
        }
    }
}

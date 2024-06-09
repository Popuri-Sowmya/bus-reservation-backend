package com.fastx.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;

import java.io.IOException;

@Service
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender emailSender;

    public void sendEmailWithAttachment(MultipartFile pdf, String recipientEmail) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("fastxcorp@gmail.com");
            helper.setTo(recipientEmail);
            helper.setSubject("Your Ticket PDF");
            helper.setText("Thank you for booking your ticket with us. Please find your ticket attached.");

            // Convert MultipartFile to byte array
            byte[] pdfBytes = pdf.getBytes();

            // Create a ByteArrayDataSource from the byte array
            ByteArrayDataSource dataSource = new ByteArrayDataSource(pdfBytes, pdf.getContentType());

            // Add the attachment to the email
            helper.addAttachment(pdf.getOriginalFilename(), dataSource);

            // Send the email
            emailSender.send(message);
        } catch (IOException e) {
            System.out.println("Error receiving file!!");
        }
    }
}

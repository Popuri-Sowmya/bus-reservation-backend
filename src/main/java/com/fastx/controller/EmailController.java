package com.fastx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fastx.serviceimpl.EmailServiceImpl;

import jakarta.mail.MessagingException;

@CrossOrigin("*")
@RestController
public class EmailController {

    @Autowired
    private EmailServiceImpl emailService;

    @PostMapping("/api/send-email")
    public void sendEmailWithAttachment(@RequestBody MultipartFile pdf, @RequestParam String recipientEmail) throws MessagingException, javax.mail.MessagingException {
        emailService.sendEmailWithAttachment(pdf, recipientEmail);
    }
}

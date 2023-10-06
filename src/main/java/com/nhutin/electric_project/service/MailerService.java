package com.nhutin.electric_project.service;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;

@Service
public interface MailerService {
    void send(MailInfo mail) throws MessagingException, IOException;

    void send(String to, String subject, String body) throws MessagingException;

    void queue(MailInfo mail);

    void queue(String to, String subject, String body) throws MessagingException;

}

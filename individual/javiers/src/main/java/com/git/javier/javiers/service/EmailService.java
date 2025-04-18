package com.git.javier.javiers.service;


import com.git.javier.javiers.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

   

    public void sendConfirmationCode(User user){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(user.getEmail());
        message.setSubject("Confirm your email");
        message.setText("hola " );
        try {
        mailSender.send(message);
        } catch (MailException e) {
            throw new RuntimeException("Failed to send confirmation email", e);
        }
        
    }
}
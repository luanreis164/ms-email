package com.ms.email.service;

import com.ms.email.enums.StatusEmail;
import com.ms.email.model.Email;
import com.ms.email.repository.EmailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    private final EmailRepository emailRepository;
    private final JavaMailSender emailSender;

    public EmailService(EmailRepository emailRepository, JavaMailSender emailSender) {
        this.emailRepository = emailRepository;
        this.emailSender = emailSender;
    }

    @Value(value = "${spring.mail.username}")
    private String emailFrom;

    @Transactional
    public Email sendEmail(Email email) {
        try {
            email.setSendDateEmail(LocalDateTime.now());
            email.setEmailFrom(emailFrom);

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email.getEmailTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getText(), true);

            emailSender.send(message);
            email.setStatusEmail(StatusEmail.SENT);

        } catch (MessagingException e) {
            email.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(email);
        }
    }

}

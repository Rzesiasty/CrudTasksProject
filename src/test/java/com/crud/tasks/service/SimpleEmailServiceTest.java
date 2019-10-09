package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    SimpleEmailService emailService;

    @Mock
    JavaMailSender mailSender;

    @Test
    public void shouldSendEmailWithoutCc() {
        //Given
        Mail mail = new Mail("test@test.com", "test", "test message");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getReceiverEmail());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        //When
        emailService.send(mail);

        //Then
        verify(mailSender, times(1)).send(mailMessage);
    }

    @Test
    public void shouldSendEmailWithCc() {
        //Given
        Mail mail = new Mail("test@test.com", "test", "test message", "test1@test.com");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getReceiverEmail());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        mailMessage.setCc(mail.getToCc());

        //When
        emailService.send(mail);

        //Then
        verify(mailSender, times(1)).send(mailMessage);
    }
}

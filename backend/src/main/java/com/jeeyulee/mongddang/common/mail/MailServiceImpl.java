package com.jeeyulee.mongddang.common.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;

    public void send(MailDTO mailDTO) throws MailSendException{
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,"UTF-8");
            helper.setFrom(from);
            helper.setTo(mailDTO.getTo());
            helper.setSubject(mailDTO.getTitle());
            helper.setText(mailDTO.getContents());

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new MailSendException();
        }
    }
}

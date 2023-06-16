package com.fmss.automotiveecombackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    @Value("${mail.outlook.sender}")
    private String sender;

    public void sendHtmlEmail(String toEmail,String subject,String body) {
        mailSender.send(mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(sender);
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(prepareMailHtml(body),true);
        });

        log.info("Html mail sent to: {} from: {} subject: {}", toEmail, sender, subject);
    }

    private String prepareMailHtml(String body) {
        return "<html>" +
                "<body>" +
                "<h3> Order Info </h3>" +
                "<p>" + body + "</p>"+
                "</body>" +
                "</html>";
    }
}

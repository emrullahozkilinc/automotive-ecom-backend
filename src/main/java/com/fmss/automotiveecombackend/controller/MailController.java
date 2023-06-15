package com.fmss.automotiveecombackend.controller;

import com.fmss.automotiveecombackend.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @GetMapping("/test")
    public ResponseEntity<String> testMail() {
        mailService.sendHtmlEmail("dossantos27131@gmail.com", "deneme maili", "bu maili sanayoluyorum ama test için");
        return ResponseEntity.ok("sended");
    }

}

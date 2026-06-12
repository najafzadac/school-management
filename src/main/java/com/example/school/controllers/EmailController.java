package com.example.school.controllers;
import com.example.school.dto.EmailRequest;
import com.example.school.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;
    @PostMapping("/send")
    public ResponseEntity<String>sendEmail (@RequestBody EmailRequest request){
        emailService.sendEmail(request);
        return ResponseEntity.ok("email send"+request.getTo());

    }
}

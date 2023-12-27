package com.example.apienglish.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.apienglish.entity.Account;

import org.springframework.mail.javamail.JavaMailSender;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    @Transactional
//    public void sendVerificationEmail(Account user) {
//        try {
//            MimeMessage message = javaMailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//            helper.setTo(user.getEmail());
//            helper.setSubject("Email Verification");
//            helper.setText(
//                "Hello " + user.getUsername() + ",\n\n"
//                + "Thank you for registering. Please click the link below to verify your email:\n\n"
//                + "Verification Link: " + "http://localhost:8080/api/auth/verify?email=" + user.getEmail() + "&code=" + user.getVerificationCode() + "\n\n"
//                + "If you didn't register on our website, please ignore this email.\n",
//                true
//            );
//
//            javaMailSender.send(message);
//        } catch (Exception e) {
//            // Xử lý các ngoại lệ khi gửi email không thành công
//            e.printStackTrace();
//        }
//    }
	
	 @Value("${spring.mail.username}")
	    private String fromEmail;

	    private final JavaMailSender javaMailSender;

	    public EmailService(JavaMailSender javaMailSender) {
	        this.javaMailSender = javaMailSender;
	    }

	    public void sendVerificationCode(String toEmail, String verificationCode) {
	        SimpleMailMessage mailMessage = new SimpleMailMessage();
	        mailMessage.setFrom(fromEmail);
	        mailMessage.setTo(toEmail);
	        mailMessage.setSubject("Email Verification Code");
	        mailMessage.setText("Your verification code is: " + verificationCode);

	        javaMailSender.send(mailMessage);
	    }
}

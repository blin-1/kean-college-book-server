package com.open.source.inc.auction.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
			
    @Autowired
	private JavaMailSender javaMailSender;

    public void sendMail(String [] emails, String subject, String message) {
        
    	SimpleMailMessage mailMessage = new SimpleMailMessage();    	
    	mailMessage.setTo(emails);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailMessage.setFrom("admin@admin.com");
        javaMailSender.send(mailMessage);
    
    }
	 	
}

package com.pos.user.management.UserService.utility;

import com.pos.user.management.UserService.model.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class PosEmailSender{

    @Autowired
    public JavaMailSender javaMailSender;

    public void sendActivationEmail(EmailRequest emailRequest){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(emailRequest.getTo());
        simpleMailMessage.setSubject(emailRequest.getSubject());
        simpleMailMessage.setText(emailRequest.getBody());
        simpleMailMessage.setFrom(emailRequest.getFrom());
        javaMailSender.send(simpleMailMessage);
    }
}

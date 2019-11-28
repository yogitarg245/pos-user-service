package com.pos.user.management.UserService.events;

import com.pos.user.management.UserService.model.EmailRequest;
import com.pos.user.management.UserService.persistance.entities.PosUsersEntity;
import com.pos.user.management.UserService.service.IPosUserService;
import com.pos.user.management.UserService.utility.PosEmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class PosUserRegistrationListener implements ApplicationListener<OnPosRegistrationCompleteEvent> {

    @Autowired
    private IPosUserService posUserService;

    @Override
    public void onApplicationEvent(OnPosRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnPosRegistrationCompleteEvent event) {
        PosUsersEntity posUsersEntity=event.getPosUsersEntity();
        String token= UUID.randomUUID().toString();
        posUserService.createVerification(posUsersEntity,token);
        String confirmationUrl=event.getAppUrl()+token;
        String message="<html><body><h1>Your registration is success. Please click on the below link to activate your account</h1></br>";
        String messageEnd="</body></html>";

        EmailRequest emailRequest=new EmailRequest();
        emailRequest.setTo(posUsersEntity.getEmailId());
        emailRequest.setSubject("Registration Confirmation");
        emailRequest.setBody(message + confirmationUrl + messageEnd);
        emailRequest.setFrom("asd");
        PosEmailSender posEmailSender=new PosEmailSender();
        posEmailSender.sendActivationEmail(emailRequest);
    }
}

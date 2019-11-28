package com.pos.user.management.UserService.serviceImpl;

import com.pos.user.management.UserService.events.OnPosRegistrationCompleteEvent;
import com.pos.user.management.UserService.exceptions.EmailExistException;
import com.pos.user.management.UserService.model.UserRegistration;
import com.pos.user.management.UserService.payload.UserCreationReponse;
import com.pos.user.management.UserService.persistance.entities.PosUsersEntity;
import com.pos.user.management.UserService.persistance.entities.PosVerificationToken;
import com.pos.user.management.UserService.persistance.repositories.PosUsersRepository;
import com.pos.user.management.UserService.persistance.repositories.PosVerificationTokenRepository;
import com.pos.user.management.UserService.service.IPosUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Locale;

@Service
@Transactional
public class PosUserServiceImpl implements IPosUserService {

    @Autowired
    private PosUsersRepository posUsersRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private PosVerificationTokenRepository tokenRepository;

    @Override
    public UserCreationReponse registerNewUser(UserRegistration userRegistration) {
        UserCreationReponse userCreationReponse = new UserCreationReponse();

        if(isEmailExist(userRegistration.getEmailId())) {
            throw new EmailExistException("There is an account with given email address: "+ userRegistration.getEmailId());
        }else {
            PosUsersEntity posUsersEntity = posUsersRepository.save(mapUserModelToEntity(userRegistration));
            userCreationReponse.setEmailId(posUsersEntity.getEmailId());
            userCreationReponse.setUserName(posUsersEntity.getFirstName() + posUsersEntity.getLastName());

            if (!posUsersEntity.isActive()) {
                userCreationReponse.setRegistrationStatus("Pending Activation");
                sendEmailActivationLink(posUsersEntity);
            }
        }
        return userCreationReponse;
    }

    private PosUsersEntity mapUserModelToEntity(UserRegistration userRegistration) {
        return new PosUsersEntity(userRegistration.getFirstName(), userRegistration.getLastName(), userRegistration.getEmailId(),
                userRegistration.getPassword(), userRegistration.getConfirmPassword(), Boolean.FALSE);
    }

    private boolean isEmailExist(String email){
       return posUsersRepository.findByEmailId(email).isPresent();
    }

    @Override
    public void sendEmailActivationLink(PosUsersEntity posUsersEntity){
        String appUrl="http://localhost:8300/userapi/v1/activateUser/";
        applicationEventPublisher.publishEvent(new OnPosRegistrationCompleteEvent(appUrl,Locale.UK,posUsersEntity));
    }

    @Override
    public void createVerification(PosUsersEntity posUsersEntity, String token) {
        final PosVerificationToken posVerificationToken=new PosVerificationToken(token,posUsersEntity);
        tokenRepository.save(posVerificationToken);
    }
}

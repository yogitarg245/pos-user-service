package com.pos.user.management.UserService.service;

import com.pos.user.management.UserService.model.UserRegistration;
import com.pos.user.management.UserService.payload.UserCreationReponse;
import com.pos.user.management.UserService.persistance.entities.PosUsersEntity;
import org.springframework.stereotype.Service;

@Service
public interface IPosUserService {
 UserCreationReponse registerNewUser(UserRegistration userRegistration);

 void sendEmailActivationLink(PosUsersEntity posUsersEntity);

 void createVerification(PosUsersEntity posUsersEntity, String token);
}

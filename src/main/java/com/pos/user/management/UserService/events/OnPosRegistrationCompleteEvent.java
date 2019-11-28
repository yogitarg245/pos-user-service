package com.pos.user.management.UserService.events;

import com.pos.user.management.UserService.persistance.entities.PosUsersEntity;
import lombok.Data;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;
@Data
@ToString
public class OnPosRegistrationCompleteEvent extends ApplicationEvent {

    private String appUrl;
    private Locale locale;
    private PosUsersEntity posUsersEntity;

    public OnPosRegistrationCompleteEvent(String appUrl, Locale locale, PosUsersEntity posUsersEntity) {
        super(posUsersEntity);
        this.appUrl = appUrl;
        this.locale = locale;
        this.posUsersEntity = posUsersEntity;
    }
}

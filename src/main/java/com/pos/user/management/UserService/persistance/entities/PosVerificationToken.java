package com.pos.user.management.UserService.persistance.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "pos_verification_token")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Audited
public class PosVerificationToken {

    private static final int EXPIRATION=60*24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tokenId;
    private String token;
    @OneToOne(targetEntity = PosUsersEntity.class,fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "user_id")
    private PosUsersEntity posUsersEntity;
    private Date expiryDate;

    public PosVerificationToken(String token, PosUsersEntity posUsersEntity) {
        this.token = token;
        this.posUsersEntity = posUsersEntity;
        this.expiryDate=calculateExpiryDate(EXPIRATION);
    }

    private Date calculateExpiryDate(int expiration) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        calendar.add(Calendar.MINUTE,expiration);
        return new Date(calendar.getTime().getTime());
    }
}

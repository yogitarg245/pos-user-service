package com.pos.user.management.UserService.model;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailRequest {

    private String to;
    @Nullable
    private String cc;
    @Nullable
    private String bcc;
    private String subject;
    @Nullable
    private String body;
    private String from;
}

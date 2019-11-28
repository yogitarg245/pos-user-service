package com.pos.user.management.UserService.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PosExceptionResponse {
    private Date date;
    private String messageType;
    private String message;
    private String details;
}

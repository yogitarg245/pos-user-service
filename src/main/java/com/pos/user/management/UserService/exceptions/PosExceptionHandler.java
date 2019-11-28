package com.pos.user.management.UserService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
    public class PosExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailExistException.class)
    protected ResponseEntity<PosExceptionResponse> handleEmailException(EmailExistException ex, WebRequest webRequest){
        PosExceptionResponse posExceptionResponse=new PosExceptionResponse(new Date(),"EMAIL_ALREADY_EXIST",ex.getMessage(),webRequest.getDescription(Boolean.FALSE));
        return new ResponseEntity<>(posExceptionResponse,HttpStatus.ALREADY_REPORTED);
    }
}

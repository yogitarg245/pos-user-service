package com.pos.user.management.UserService.model;

import com.pos.user.management.UserService.validation.EmailValidator;
import com.pos.user.management.UserService.validation.PasswordMatcher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@PasswordMatcher
public class UserRegistration {

    @NotEmpty
    @NotNull
    private String firstName;
    @NotEmpty
    @NotNull
    private String lastName;
    @NotEmpty
    @NotNull
    @EmailValidator
    private String emailId;
    @NotEmpty
    @NotNull
    private String password;
    @NotEmpty
    @NotNull
    private String confirmPassword;

}

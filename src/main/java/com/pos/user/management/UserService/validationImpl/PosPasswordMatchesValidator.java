package com.pos.user.management.UserService.validationImpl;

import com.pos.user.management.UserService.model.UserRegistration;
import com.pos.user.management.UserService.validation.PasswordMatcher;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PosPasswordMatchesValidator implements ConstraintValidator<PasswordMatcher,UserRegistration> {
    @Override
    public void initialize(PasswordMatcher constraintAnnotation) {

    }

    @Override
    public boolean isValid(UserRegistration userRegistration, ConstraintValidatorContext constraintValidatorContext) {
        return userRegistration.getPassword().equals(userRegistration.getConfirmPassword());
    }
}

package com.pos.user.management.UserService.validation;

import com.pos.user.management.UserService.validationImpl.PosPasswordMatchesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PosPasswordMatchesValidator.class)
@Documented
public @interface PasswordMatcher {
    String message() default "Password is not same";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

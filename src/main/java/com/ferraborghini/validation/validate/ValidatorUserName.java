package com.ferraborghini.validation.validate;

import com.ferraborghini.validation.validator.UserNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserNameValidator.class)
public @interface ValidatorUserName {
    int[] array() default {-1, 1};

    String message() default "您的数据不合法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
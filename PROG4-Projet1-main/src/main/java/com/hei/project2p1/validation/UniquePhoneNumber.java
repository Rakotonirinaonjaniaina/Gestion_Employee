package com.hei.project2p1.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniquePhoneNumberValidator.class)
@Documented
public @interface UniquePhoneNumber {
    String message() default "Les numéros de téléphone doivent être uniques pour chaque pays";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

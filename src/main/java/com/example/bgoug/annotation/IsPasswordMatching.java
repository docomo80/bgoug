package com.example.bgoug.annotation;

import com.example.bgoug.validator.IsPasswordMatchingValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = IsPasswordMatchingValidator.class)
public @interface IsPasswordMatching {

    String message() default "Паролите не съвпадат";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

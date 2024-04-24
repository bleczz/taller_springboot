package com.pretaller.domain.dto;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Size(min = 8, max = 32, message = "Password should be between 8 and 32 characters")
@Pattern(regexp = ".*[A-Z].*", message = "Password should contain at least one uppercase letter")
@Pattern(regexp = ".*[0-9].*", message = "Password should contain at least one number")
@Pattern(regexp = ".*[!_#$].*", message = "Password should contain at least one of the special characters: !_#$")
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
public @interface PasswordConstraints {
    String message() default "Invalid password";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
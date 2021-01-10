package com.mvnikitin.eshop.constraints;

import com.mvnikitin.eshop.dto.RegisterForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements
        ConstraintValidator<PasswordMatch, RegisterForm> {

    public void initialize(PasswordMatch constraintAnnotation) {
    }

    public boolean isValid(RegisterForm value,
                           ConstraintValidatorContext context) {

        if (value.getPassword() != null && value.getRepeatPassword() != null) {
            return value.getPassword().equals(value.getRepeatPassword());
        }

        return false;
    }
}

package com.mvnikitin.eshop.constraints;

import com.mvnikitin.eshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Service
public class NotExistsUserValidator implements
        ConstraintValidator<NotExistsUser, String> {

    UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(NotExistsUser constraintAnnotation) {}

    @Override
    public boolean isValid(String username,
                           ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.findOneByUsername(username) == null;
    }
}

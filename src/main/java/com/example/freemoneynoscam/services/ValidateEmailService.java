package com.example.freemoneynoscam.services;

import org.apache.commons.validator.routines.EmailValidator;

public class ValidateEmailService {
    public boolean isEmailValid(String email){
        EmailValidator validator = EmailValidator.getInstance();

        return validator.isValid(email);
    }
}

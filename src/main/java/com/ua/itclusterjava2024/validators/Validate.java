package com.ua.itclusterjava2024.validators;

import org.springframework.validation.Validator;

public interface Validate extends Validator {
    static boolean checkNullAndEmpty(String string){
        return string == null
                || string.isBlank();
    }
}

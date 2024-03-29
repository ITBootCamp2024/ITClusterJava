package com.ua.itclusterjava2024.validators;

import com.ua.itclusterjava2024.dto.SchoolDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SchoolValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return SchoolDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SchoolDTO schoolDTO = (SchoolDTO) target;

        if (checkNullAndEmpty(schoolDTO.getName())) {
            errors.rejectValue("name", "", "Name mustn't be empty");
        }
        if (checkNullAndEmpty(schoolDTO.getSite())) {
            errors.rejectValue("site", "", "Site mustn't be empty");
        }
        if (checkNullAndEmpty(schoolDTO.getDescription())) {
            errors.rejectValue("description", "", "Description mustn't be empty");
        }
        if (checkNullAndEmpty(schoolDTO.getContact())) {
            errors.rejectValue("contact", "", "Contact mustn't be empty");
        }
    }

    private static boolean checkNullAndEmpty(String string) {
        return string == null
                || string.isBlank();
    }
}

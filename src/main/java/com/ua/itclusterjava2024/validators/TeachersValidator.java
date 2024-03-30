package com.ua.itclusterjava2024.validators;

import com.ua.itclusterjava2024.dto.TeachersDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class TeachersValidator implements Validate{
    @Override
    public boolean supports(Class<?> clazz) {
        return TeachersDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TeachersDTO teachersDTO = (TeachersDTO) target;
//
//        if (Validate.checkNullAndEmpty(teachersDTO.getName())) {
//            errors.rejectValue("name", "", "Name mustn't be empty");
//        }
//        if (Validate.checkNullAndEmpty(teachersDTO.getRole())) {
//            errors.rejectValue("role", "", "Role mustn't be empty");
//        }
//        if (Validate.checkNullAndEmpty(teachersDTO.getStatus())) {
//            errors.rejectValue("status", "", "Status mustn't be empty");
//        }
//        if (Validate.checkNullAndEmpty(teachersDTO.getEmail())) {
//            errors.rejectValue("email", "", "email mustn't be empty");
//        }
//        if (Validate.checkNullAndEmpty(teachersDTO.getDetails())) {
//            errors.rejectValue("details", "", "details mustn't be empty");
//        }
    }
}

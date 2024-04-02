package com.ua.itclusterjava2024.validators;

import com.ua.itclusterjava2024.dto.SpecialtyDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SpecialtyValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return SpecialtyDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
//        SpecialtyDTO specialtyDTO = (SpecialtyDTO) target;
//
//        if (checkNullAndEmpty(specialtyDTO.getName())) {
//            errors.rejectValue("name", "", "Name mustn't be empty");
//        }
//        if (checkNullAndEmpty(specialtyDTO.getLink_standart())) {
//            errors.rejectValue("link_standart", "", "Link standart mustn't be empty");
//        }

    }

    private static boolean checkNullAndEmpty(String string) {
        return string == null
                || string.isBlank();
    }
}

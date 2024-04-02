package com.ua.itclusterjava2024.validators;

import com.ua.itclusterjava2024.dto.EducationLevelsDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProgramsLevelValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return EducationLevelsDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EducationLevelsDTO educationLevelsDTO = (EducationLevelsDTO) target;

        if (checkNullAndEmpty(educationLevelsDTO.getName())) {
            errors.rejectValue("name", "", "Name mustn't be empty");
        }
    }

    private static boolean checkNullAndEmpty(String string) {
        return string == null
                || string.isBlank();
    }
}

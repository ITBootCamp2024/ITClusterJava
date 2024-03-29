package com.ua.itclusterjava2024.validators;

import com.ua.itclusterjava2024.dto.ProgramsLevelDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProgramsLevelValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ProgramsLevelDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProgramsLevelDTO programsLevelDTO = (ProgramsLevelDTO) target;

        if (checkNullAndEmpty(programsLevelDTO.getName())) {
            errors.rejectValue("name", "", "Name mustn't be empty");
        }
    }

    private static boolean checkNullAndEmpty(String string) {
        return string == null
                || string.isBlank();
    }
}

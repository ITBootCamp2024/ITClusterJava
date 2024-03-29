package com.ua.itclusterjava2024.validators;

import com.ua.itclusterjava2024.dto.ProgramsDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProgramsValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ProgramsDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProgramsDTO programsDTO = (ProgramsDTO) target;

        if (checkNullAndEmpty(programsDTO.getName())) {
            errors.rejectValue("name", "", "Name mustn't be empty");
        }
        if (checkNullAndEmpty(programsDTO.getProgram_link())) {
            errors.rejectValue("program_link", "", "Program link mustn't be empty");
        }
        if (checkNullAndEmpty(programsDTO.getGarant())) {
            errors.rejectValue("garant", "", "Garant mustn't be empty");
        }
        if (checkNullAndEmpty(programsDTO.getSchool_name())) {
            errors.rejectValue("school_name", "", "School name mustn't be empty");
        }
        if (checkNullAndEmpty(programsDTO.getSchool_link())) {
            errors.rejectValue("school_link", "", "School link mustn't be empty");
        }
        if (checkNullAndEmpty(programsDTO.getClabus_link())) {
            errors.rejectValue("clabus_link", "", "Clabus link mustn't be empty");
        }
    }

    private static boolean checkNullAndEmpty(String string) {
        return string == null
                || string.isBlank();
    }
}

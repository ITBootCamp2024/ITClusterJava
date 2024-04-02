package com.ua.itclusterjava2024.validators;

import com.ua.itclusterjava2024.dto.EducationProgramsDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProgramsValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return EducationProgramsDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
//        EducationProgramsDTO educationProgramsDTO = (EducationProgramsDTO) target;
//
//        if (checkNullAndEmpty(educationProgramsDTO.getName())) {
//            errors.rejectValue("name", "", "Name mustn't be empty");
//        }
//        if (checkNullAndEmpty(educationProgramsDTO.getProgram_link())) {
//            errors.rejectValue("program_link", "", "Program link mustn't be empty");
//        }
//        if (checkNullAndEmpty(educationProgramsDTO.getGarant())) {
//            errors.rejectValue("garant", "", "Garant mustn't be empty");
//        }
//        if (checkNullAndEmpty(educationProgramsDTO.getSchool_name())) {
//            errors.rejectValue("school_name", "", "School name mustn't be empty");
//        }
//        if (checkNullAndEmpty(educationProgramsDTO.getSchool_link())) {
//            errors.rejectValue("school_link", "", "School link mustn't be empty");
//        }
//        if (checkNullAndEmpty(educationProgramsDTO.getClabus_link())) {
//            errors.rejectValue("clabus_link", "", "Clabus link mustn't be empty");
//        }
    }

    private static boolean checkNullAndEmpty(String string) {
        return string == null
                || string.isBlank();
    }
}

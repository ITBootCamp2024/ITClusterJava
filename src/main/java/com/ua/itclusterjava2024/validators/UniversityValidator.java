package com.ua.itclusterjava2024.validators;

import com.ua.itclusterjava2024.dto.UniversityDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UniversityValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UniversityDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UniversityDTO universityDTO = (UniversityDTO) target;

        if (checkNullAndEmpty(universityDTO.getName())) {
            errors.rejectValue("name", "", "Name mustn't be empty");
        }
//        if (checkNullAndEmpty(universityDTO.getShortname())) {
//            errors.rejectValue("shortname", "", "Shortname mustn't be empty");
//        }
//        if (checkNullAndEmpty(universityDTO.getSiteLink())) {
//            errors.rejectValue("siteLink", "", "Sitelink mustn't be empty");
//        }
//        if (checkNullAndEmpty(universityDTO.getPrograms_list())) {
//            errors.rejectValue("programs_list", "", "Programs list mustn't be empty");
//        }
    }

    private static boolean checkNullAndEmpty(String string) {
        return string == null
                || string.isBlank();
    }
}

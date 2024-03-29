package com.ua.itclusterjava2024.validators;

import com.ua.itclusterjava2024.dto.CourseStatusDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CourseStatusValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return CourseStatusDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CourseStatusDTO courseStatusDTO = (CourseStatusDTO) target;

        if (checkNullAndEmpty(courseStatusDTO.getName())) {
            errors.rejectValue("name", "", "Name mustn't be empty");
        }
        if (checkNullAndEmpty(courseStatusDTO.getDescription())) {
            errors.rejectValue("description", "", "Description mustn't be empty");
        }
    }

    private static boolean checkNullAndEmpty(String string) {
        return string == null
                || string.isBlank();
    }
}

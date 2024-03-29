package com.ua.itclusterjava2024.validators;

import com.ua.itclusterjava2024.dto.CourseGroupDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CourseGroupValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return CourseGroupDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CourseGroupDTO courseGroupDTO = (CourseGroupDTO) target;

        if (checkNullAndEmpty(courseGroupDTO.getName())) {
            errors.rejectValue("name", "", "Name mustn't be empty");
        }
        if (checkNullAndEmpty(courseGroupDTO.getDescription())) {
            errors.rejectValue("description", "", "Description mustn't be empty");
        }
    }

    private static boolean checkNullAndEmpty(String string) {
        return string == null
                || string.isBlank();
    }
}

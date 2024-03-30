package com.ua.itclusterjava2024.validators;

import com.ua.itclusterjava2024.dto.CourseBlockDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class CourseBlockValidator implements Validate{
    @Override
    public boolean supports(Class<?> clazz) {
        return CourseBlockDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CourseBlockDTO courseBlockDTO = (CourseBlockDTO) target;

        if (Validate.checkNullAndEmpty(courseBlockDTO.getName())) {
            errors.rejectValue("name", "", "Name mustn't be empty");
        }
        if (Validate.checkNullAndEmpty(courseBlockDTO.getDescription())) {
            errors.rejectValue("description", "", "Description mustn't be empty");
        }
    }
}

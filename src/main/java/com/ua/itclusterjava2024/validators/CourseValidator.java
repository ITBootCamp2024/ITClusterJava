package com.ua.itclusterjava2024.validators;

import com.ua.itclusterjava2024.dto.CourseDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class CourseValidator implements Validate{
    @Override
    public boolean supports(Class<?> clazz) {
        return CourseDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CourseDTO courseDTO = (CourseDTO) target;

        if (Validate.checkNullAndEmpty(courseDTO.getName())) {
            errors.rejectValue("name", "", "Name mustn't be empty");
        }
        if (Validate.checkNullAndEmpty(courseDTO.getSyllabusLink())) {
            errors.rejectValue("syllabusLink", "", "syllabusLink mustn't be empty");
        }
        if (Validate.checkNullAndEmpty(courseDTO.getWorkProgramLink())) {
            errors.rejectValue("workProgramLink", "", "Work program link mustn't be empty");
        }
        if (Validate.checkNullAndEmpty(courseDTO.getReviewLink())) {
            errors.rejectValue("reviewLink", "", "Review link mustn't be empty");
        }
    }
}

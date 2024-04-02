package com.ua.itclusterjava2024.validators;

import com.ua.itclusterjava2024.dto.DisciplineGroupsDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CourseGroupValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return DisciplineGroupsDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DisciplineGroupsDTO disciplineGroupsDTO = (DisciplineGroupsDTO) target;

        if (checkNullAndEmpty(disciplineGroupsDTO.getName())) {
            errors.rejectValue("name", "", "Name mustn't be empty");
        }
        if (checkNullAndEmpty(disciplineGroupsDTO.getDescription())) {
            errors.rejectValue("description", "", "Description mustn't be empty");
        }
    }

    private static boolean checkNullAndEmpty(String string) {
        return string == null
                || string.isBlank();
    }
}

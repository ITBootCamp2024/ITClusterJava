package com.ua.itclusterjava2024.validators;

import com.ua.itclusterjava2024.dto.DisciplineBlocksDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class CourseBlockValidator implements Validate{
    @Override
    public boolean supports(Class<?> clazz) {
        return DisciplineBlocksDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DisciplineBlocksDTO disciplineBlocksDTO = (DisciplineBlocksDTO) target;

        if (Validate.checkNullAndEmpty(disciplineBlocksDTO.getName())) {
            errors.rejectValue("name", "", "Name mustn't be empty");
        }
        if (Validate.checkNullAndEmpty(disciplineBlocksDTO.getDescription())) {
            errors.rejectValue("description", "", "Description mustn't be empty");
        }
    }
}

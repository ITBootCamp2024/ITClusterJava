package com.ua.itclusterjava2024.validators;

import com.ua.itclusterjava2024.dto.DisciplineBlocksDTO;
import com.ua.itclusterjava2024.dto.PositionDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class PositionValidator implements Validate{
    @Override
    public boolean supports(Class<?> clazz) {
        return PositionDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PositionDTO positionDTO = (PositionDTO) target;

        if (Validate.checkNullAndEmpty(positionDTO.getName())) {
            errors.rejectValue("name", "", "Name mustn't be empty");
        }
        if (Validate.checkNullAndEmpty(positionDTO.getDescription())) {
            errors.rejectValue("description", "", "Description mustn't be empty");
        }
    }
}

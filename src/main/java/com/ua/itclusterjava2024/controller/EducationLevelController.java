package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.EducationLevelDTO;
import com.ua.itclusterjava2024.entity.EducationLevel;
import com.ua.itclusterjava2024.exceptions.ValidationException;
import com.ua.itclusterjava2024.service.interfaces.EducationLevelsService;
import com.ua.itclusterjava2024.validators.ProgramsLevelValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/education-levels")
public class EducationLevelController {

    private final EducationLevelsService educationLevelsService;
    private final ModelMapper modelMapper;
    private final ProgramsLevelValidator programsLevelValidator;

    @Autowired
    public EducationLevelController(EducationLevelsService educationLevelsService, ModelMapper modelMapper, ProgramsLevelValidator programsLevelValidator) {
        this.educationLevelsService = educationLevelsService;
        this.modelMapper = modelMapper;
        this.programsLevelValidator = programsLevelValidator;
    }

    @GetMapping
    public List<EducationLevelDTO> findAll() {
        return educationLevelsService.getAll().stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EducationLevelDTO findById(@PathVariable long id) {
        return convertToDTO(educationLevelsService.readById(id).orElse(null));
    }

    @PostMapping
    public RedirectView save(@RequestBody @Valid EducationLevelDTO educationLevelDTO, BindingResult bindingResult) {
        programsLevelValidator.validate(educationLevelDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        educationLevelsService.create(convertToEntity(educationLevelDTO));
        return new RedirectView("/education-levels");
    }

    @PatchMapping("/{id}")
    public RedirectView update(@PathVariable("id") Long id,
            @RequestBody @Valid EducationLevelDTO educationLevelDTO,
                               BindingResult bindingResult
    ) {
        programsLevelValidator.validate(educationLevelDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        educationLevelsService.update(id, convertToEntity(educationLevelDTO));
        return new RedirectView("/education-levels");
    }

    @DeleteMapping("/{id}")
    public RedirectView delete(@PathVariable long id) {
        educationLevelsService.delete(id);
        return new RedirectView("/education-levels");
    }

    private EducationLevel convertToEntity(EducationLevelDTO educationLevelDTO){
        return modelMapper.map(educationLevelDTO, EducationLevel.class);
    }

    private EducationLevelDTO convertToDTO(EducationLevel educationLevel){
        return modelMapper.map(educationLevel, EducationLevelDTO.class);
    }
}

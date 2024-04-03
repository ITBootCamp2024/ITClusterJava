package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.EducationLevelsDTO;
import com.ua.itclusterjava2024.entity.EducationLevels;
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
public class EducationLevelsController {

    private final EducationLevelsService educationLevelsService;
    private final ModelMapper modelMapper;
    private final ProgramsLevelValidator programsLevelValidator;

    @Autowired
    public EducationLevelsController(EducationLevelsService educationLevelsService, ModelMapper modelMapper, ProgramsLevelValidator programsLevelValidator) {
        this.educationLevelsService = educationLevelsService;
        this.modelMapper = modelMapper;
        this.programsLevelValidator = programsLevelValidator;
    }

    @GetMapping
    public List<EducationLevelsDTO> findAll() {
        return educationLevelsService.getAll().stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EducationLevelsDTO findById(@PathVariable long id) {
        return convertToDTO(educationLevelsService.readById(id).orElse(null));
    }

    @PostMapping
    public RedirectView save(@RequestBody @Valid EducationLevelsDTO educationLevelsDTO, BindingResult bindingResult) {
        programsLevelValidator.validate(educationLevelsDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        educationLevelsService.create(convertToEntity(educationLevelsDTO));
        return new RedirectView("/education-levels");
    }

    @PatchMapping("/{id}")
    public RedirectView update(@PathVariable("id") Long id,
            @RequestBody @Valid EducationLevelsDTO educationLevelsDTO,
                               BindingResult bindingResult
    ) {
        programsLevelValidator.validate(educationLevelsDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        educationLevelsService.update(id, convertToEntity(educationLevelsDTO));
        return new RedirectView("/education-levels");
    }

    @DeleteMapping("/{id}")
    public RedirectView delete(@PathVariable long id) {
        educationLevelsService.delete(id);
        return new RedirectView("/education-levels");
    }

    private EducationLevels convertToEntity(EducationLevelsDTO educationLevelsDTO){
        return modelMapper.map(educationLevelsDTO, EducationLevels.class);
    }

    private EducationLevelsDTO convertToDTO(EducationLevels educationLevels){
        return modelMapper.map(educationLevels, EducationLevelsDTO.class);
    }
}

package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.ProgramsDTO;
import com.ua.itclusterjava2024.entity.EducationPrograms;
import com.ua.itclusterjava2024.exceptions.ValidationException;
import com.ua.itclusterjava2024.service.interfaces.EducationProgramsService;

import com.ua.itclusterjava2024.validators.ProgramsValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/programs")
public class EducationController {
    private final EducationProgramsService educationProgramsService;

    private final ModelMapper modelMapper;
    private final ProgramsValidator programsValidator;

    @Autowired
    public EducationController(EducationProgramsService educationProgramsService, ModelMapper modelMapper, ProgramsValidator programsValidator) {
        this.educationProgramsService = educationProgramsService;
        this.modelMapper = modelMapper;
        this.programsValidator = programsValidator;
    }

    @GetMapping
    public List<ProgramsDTO> findAll() {
        return educationProgramsService.getAll().stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProgramsDTO findById(@PathVariable long id) {
        return convertToDTO(educationProgramsService.readById(id).orElse(null));
    }

    @PostMapping
    public RedirectView save(@RequestBody @Valid ProgramsDTO programsDTO, BindingResult bindingResult) {
        programsValidator.validate(programsDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        educationProgramsService.create(convertToEntity(programsDTO));
        return new RedirectView("/programs");
    }

    @PatchMapping("/{id}")
    public RedirectView update(@PathVariable("id") Long id,
            @RequestBody @Valid ProgramsDTO programsDTO,
                               BindingResult bindingResult
    ) {
        programsValidator.validate(programsDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        educationProgramsService.update(id, convertToEntity(programsDTO));
        return new RedirectView("/course_blocks");
    }

    @DeleteMapping("/{id}")
    public RedirectView delete(@PathVariable long id) {
        educationProgramsService.delete(id);
        return new RedirectView("/programs");
    }

    private EducationPrograms convertToEntity(ProgramsDTO programsDTO){
        return modelMapper.map(programsDTO, EducationPrograms.class);
    }

    private ProgramsDTO convertToDTO(EducationPrograms educationPrograms){
        return modelMapper.map(educationPrograms, ProgramsDTO.class);
    }
}

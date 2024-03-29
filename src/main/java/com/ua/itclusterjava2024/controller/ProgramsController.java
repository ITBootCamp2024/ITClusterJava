package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.ProgramsDTO;
import com.ua.itclusterjava2024.entity.Programs;
import com.ua.itclusterjava2024.exceptions.ValidationException;
import com.ua.itclusterjava2024.service.interfaces.ProgramsService;
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
public class ProgramsController {
    private final ProgramsService programsService;

    private final ModelMapper modelMapper;
    private final ProgramsValidator programsValidator;

    @Autowired
    public ProgramsController(ProgramsService programsService, ModelMapper modelMapper, ProgramsValidator programsValidator) {
        this.programsService = programsService;
        this.modelMapper = modelMapper;
        this.programsValidator = programsValidator;
    }

    @GetMapping
    public List<ProgramsDTO> findAll() {
        return programsService.getAll().stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProgramsDTO findById(@PathVariable long id) {
        return convertToDTO(programsService.readById(id));
    }

    @PostMapping
    public RedirectView save(@RequestBody @Valid ProgramsDTO programsDTO, BindingResult bindingResult) {
        programsValidator.validate(programsDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        programsService.create(convertToEntity(programsDTO));
        return new RedirectView("/programs");
    }

    @PutMapping("/{id}")
    public RedirectView update(@PathVariable("id") Long id,
            @RequestBody @Valid ProgramsDTO programsDTO,
                               BindingResult bindingResult
    ) {
        programsValidator.validate(programsDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        programsService.update(id, convertToEntity(programsDTO));
        return new RedirectView("/course_blocks");
    }

    @DeleteMapping("/{id}")
    public RedirectView delete(@PathVariable long id) {
        programsService.delete(id);
        return new RedirectView("/programs");
    }

    private Programs convertToEntity(ProgramsDTO programsDTO){
        return modelMapper.map(programsDTO, Programs.class);
    }

    private ProgramsDTO convertToDTO(Programs programs){
        return modelMapper.map(programs, ProgramsDTO.class);
    }
}

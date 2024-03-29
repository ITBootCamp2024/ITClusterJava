package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.ProgramsLevelDTO;
import com.ua.itclusterjava2024.entity.ProgramsLevel;
import com.ua.itclusterjava2024.exceptions.ValidationException;
import com.ua.itclusterjava2024.service.interfaces.ProgramsLevelService;
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
@RequestMapping("/programs_levels")
public class ProgramsLevelController {

    private final ProgramsLevelService programsLevelService;
    private final ModelMapper modelMapper;
    private final ProgramsLevelValidator programsLevelValidator;

    @Autowired
    public ProgramsLevelController(ProgramsLevelService programsLevelService, ModelMapper modelMapper, ProgramsLevelValidator programsLevelValidator) {
        this.programsLevelService = programsLevelService;
        this.modelMapper = modelMapper;
        this.programsLevelValidator = programsLevelValidator;
    }

    @GetMapping
    public List<ProgramsLevelDTO> findAll() {
        return programsLevelService.getAll().stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProgramsLevelDTO findById(@PathVariable long id) {
        return convertToDTO(programsLevelService.readById(id));
    }

    @PostMapping
    public RedirectView save(@RequestBody @Valid ProgramsLevelDTO programsLevelDTO, BindingResult bindingResult) {
        programsLevelValidator.validate(programsLevelDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        programsLevelService.create(convertToEntity(programsLevelDTO));
        return new RedirectView("/programs_levels");
    }

    @PutMapping("/{id}")
    public RedirectView update(@PathVariable("id") Long id,
            @RequestBody @Valid ProgramsLevelDTO programsLevelDTO,
                               BindingResult bindingResult
    ) {
        programsLevelValidator.validate(programsLevelDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        programsLevelService.update(id, convertToEntity(programsLevelDTO));
        return new RedirectView("/programs_levels");
    }

    @DeleteMapping("/{id}")
    public RedirectView delete(@PathVariable long id) {
        programsLevelService.delete(id);
        return new RedirectView("/programs_levels");
    }

    private ProgramsLevel convertToEntity(ProgramsLevelDTO programsLevelDTO){
        return modelMapper.map(programsLevelDTO, ProgramsLevel.class);
    }

    private ProgramsLevelDTO convertToDTO(ProgramsLevel programsLevel){
        return modelMapper.map(programsLevel, ProgramsLevelDTO.class);
    }
}

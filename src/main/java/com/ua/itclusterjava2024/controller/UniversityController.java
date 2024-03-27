package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.UniversityDTO;
import com.ua.itclusterjava2024.entity.University;

import com.ua.itclusterjava2024.exceptions.ValidationException;
import com.ua.itclusterjava2024.service.interfaces.UniversityService;
import com.ua.itclusterjava2024.validators.UniversityValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;

import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/university")
public class UniversityController {

    private final UniversityService universityService;
    private final ModelMapper modelMapper;
    private final UniversityValidator universityValidator;

    @Autowired
    public UniversityController(UniversityService universityService, ModelMapper modelMapper, UniversityValidator universityValidator) {
        this.universityService = universityService;
        this.modelMapper = modelMapper;
        this.universityValidator = universityValidator;
    }

    @GetMapping
    public List<UniversityDTO> findAll() {
        return universityService.getAll().stream().map(i -> convertToDTO(i))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UniversityDTO findById(@PathVariable long id) {
        return convertToDTO(universityService.readById(id));
    }

    @PostMapping
    public ModelAndView save(@RequestBody @Valid UniversityDTO universityDTO, BindingResult bindingResult) {
        universityValidator.validate(universityDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        universityService.create(convertToEntity(universityDTO));
        return new ModelAndView("redirect:/course_blocks");
    }

    @PutMapping("/{id}")
    public ModelAndView update(@PathVariable("id") Long id,
            @RequestBody @Valid UniversityDTO universityDTO,
                               BindingResult bindingResult
    ) {
        universityValidator.validate(universityDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        universityService.update(id, convertToEntity(universityDTO));
        return new ModelAndView("redirect:/course_blocks");
    }

    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable long id) {
        universityService.delete(id);
        return new ModelAndView("redirect:/course_blocks");
    }

    private University convertToEntity(UniversityDTO universityDTO){
        return modelMapper.map(universityDTO, University.class);
    }

    private UniversityDTO convertToDTO(University university){
        return modelMapper.map(university, UniversityDTO.class);
    }
}

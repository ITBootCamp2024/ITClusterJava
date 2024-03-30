package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.CourseDTO;
import com.ua.itclusterjava2024.entity.Disciplines;
import com.ua.itclusterjava2024.exceptions.ValidationException;
import com.ua.itclusterjava2024.service.interfaces.DisciplineService;
import com.ua.itclusterjava2024.validators.CourseValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/discipline")
public class DisciplineController {
    private final DisciplineService disciplineService;
    private final ModelMapper modelMapper;
    private final CourseValidator courseValidator;

    @Autowired
    public DisciplineController(DisciplineService disciplineService, ModelMapper modelMapper, CourseValidator courseValidator) {
        this.disciplineService = disciplineService;
        this.modelMapper = modelMapper;
        this.courseValidator = courseValidator;
    }

    @GetMapping
    public List<CourseDTO> findAll() {
        return disciplineService.getAll().stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public CourseDTO findById(@PathVariable long id) {
        return convertToDTO(disciplineService.readById(id).orElse(null));
    }

    @PostMapping
    public RedirectView save(@RequestBody @Valid CourseDTO courseDTO,
                             BindingResult bindingResult) {
        courseValidator.validate(courseDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        disciplineService.create(convertToEntity(courseDTO));
        return new RedirectView("/course");
    }

    @PatchMapping("/{id}")
    public RedirectView update(@PathVariable("id") Long id,
            @RequestBody @Valid CourseDTO courseDTO,
                               BindingResult bindingResult
    ) {
        courseValidator.validate(courseDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        disciplineService.update(id, convertToEntity(courseDTO));
        return new RedirectView("/course");
    }

    @DeleteMapping("/{id}")
    public RedirectView delete(@PathVariable long id) {
        disciplineService.delete(id);
        return new RedirectView("/course");
    }

    private Disciplines convertToEntity(CourseDTO courseDTO){
        return modelMapper.map(courseDTO, Disciplines.class);
    }

    private CourseDTO convertToDTO(Disciplines disciplines){
        return modelMapper.map(disciplines, CourseDTO.class);
    }
}
package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.CourseDTO;
import com.ua.itclusterjava2024.entity.Course;
import com.ua.itclusterjava2024.exceptions.ValidationException;
import com.ua.itclusterjava2024.service.interfaces.CourseService;
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
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;
    private final ModelMapper modelMapper;
    private final CourseValidator courseValidator;

    @Autowired
    public CourseController(CourseService courseService, ModelMapper modelMapper, CourseValidator courseValidator) {
        this.courseService = courseService;
        this.modelMapper = modelMapper;
        this.courseValidator = courseValidator;
    }

    @GetMapping
    public List<CourseDTO> findAll() {
        return courseService.getAll().stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public CourseDTO findById(@PathVariable long id) {
        return convertToDTO(courseService.readById(id));
    }

    @PostMapping
    public RedirectView save(@RequestBody @Valid CourseDTO courseDTO,
                             BindingResult bindingResult) {
        courseValidator.validate(courseDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        courseService.create(convertToEntity(courseDTO));
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
        courseService.update(id, convertToEntity(courseDTO));
        return new RedirectView("/course");
    }

    @DeleteMapping("/{id}")
    public RedirectView delete(@PathVariable long id) {
        courseService.delete(id);
        return new RedirectView("/course");
    }

    private Course convertToEntity(CourseDTO courseDTO){
        return modelMapper.map(courseDTO, Course.class);
    }

    private CourseDTO convertToDTO(Course course){
        return modelMapper.map(course, CourseDTO.class);
    }
}

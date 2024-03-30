package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.CourseStatusDTO;
import com.ua.itclusterjava2024.entity.CourseStatus;
import com.ua.itclusterjava2024.exceptions.ValidationException;
import com.ua.itclusterjava2024.service.interfaces.CourseStatusService;
import com.ua.itclusterjava2024.validators.CourseStatusValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/course_statuses")
public class CourseStatusController {
    private final CourseStatusService courseStatusService;
    private final ModelMapper modelMapper;
    private final CourseStatusValidator courseStatusValidator;

    public CourseStatusController(CourseStatusService courseStatusService, ModelMapper modelMapper, CourseStatusValidator courseStatusValidator) {
        this.courseStatusService = courseStatusService;
        this.modelMapper = modelMapper;
        this.courseStatusValidator = courseStatusValidator;
    }

    @GetMapping
    public List<CourseStatusDTO> showCourseStatusesList(){
        return courseStatusService.getAll().stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public RedirectView saveCourseStatus(@RequestBody @Valid CourseStatusDTO courseStatusDTO,
                                         BindingResult bindingResult){
        courseStatusValidator.validate(courseStatusDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        courseStatusService.create(convertToEntity(courseStatusDTO));
        return new RedirectView("redirect:/course_statuses");
    }

    @PatchMapping("/{id}")
    public RedirectView updateCourseStatus(@PathVariable("id") Long id,
                                         @RequestBody @Valid CourseStatusDTO courseStatusDTO,
                                           BindingResult bindingResult){
        courseStatusValidator.validate(courseStatusDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        courseStatusService.update(id, convertToEntity(courseStatusDTO));
        return new RedirectView("redirect:/course_statuses");
    }

    @GetMapping("/{id}")
    public CourseStatusDTO findById(@PathVariable Long id){
        return convertToDTO(courseStatusService.readById(id).orElse(null));
    }

    @DeleteMapping("/{id}")
    public RedirectView deleteCourseStatus(@PathVariable Long id){
        courseStatusService.delete(id);
        return new RedirectView("redirect:/course_statuses");
    }

    private CourseStatus convertToEntity(CourseStatusDTO courseStatusDTO){
        return modelMapper.map(courseStatusDTO, CourseStatus.class);
    }

    private CourseStatusDTO convertToDTO(CourseStatus courseStatus){
        return modelMapper.map(courseStatus, CourseStatusDTO.class);
    }
}

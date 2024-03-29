package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.CourseGroupDTO;
import com.ua.itclusterjava2024.entity.CourseGroup;
import com.ua.itclusterjava2024.exceptions.ValidationException;
import com.ua.itclusterjava2024.service.interfaces.CourseGroupService;
import com.ua.itclusterjava2024.validators.CourseGroupValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/course_groupes")
public class CourseGroupController {
    private final CourseGroupService courseGroupService;
    private final ModelMapper modelMapper;
    private final CourseGroupValidator courseGroupValidator;

    public CourseGroupController(CourseGroupService courseGroupService, ModelMapper modelMapper, CourseGroupValidator courseGroupValidator) {
        this.courseGroupService = courseGroupService;
        this.modelMapper = modelMapper;
        this.courseGroupValidator = courseGroupValidator;
    }

    @GetMapping
    public List<CourseGroupDTO> showCourseGroupesList(){
        return courseGroupService.getAll().stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public RedirectView saveCourseGroup(@RequestBody @Valid CourseGroupDTO courseGroupDTO,
                                        BindingResult bindingResult){
        courseGroupValidator.validate(courseGroupDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        courseGroupService.create(convertToEntity(courseGroupDTO));
        return new RedirectView("/course_groupes");
    }

    @PutMapping("/{id}")
    public RedirectView updateCourseGroup(@PathVariable("id") Long id,
                                           @RequestBody @Valid CourseGroupDTO courseGroupDTO,
                                          BindingResult bindingResult){
        courseGroupValidator.validate(courseGroupDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        courseGroupService.update(id, convertToEntity(courseGroupDTO));
        return new RedirectView("/course_groupes");
    }

    @GetMapping("/{id}")
    public CourseGroupDTO findById(@PathVariable Long id){
        return convertToDTO(courseGroupService.readById(id));
    }

    @DeleteMapping("/{id}")
    public RedirectView deleteCourseGroup(@PathVariable Long id){
        courseGroupService.delete(id);
        return new RedirectView("/course_groupes");
    }

    private CourseGroup convertToEntity(CourseGroupDTO courseGroupDTO){
        return modelMapper.map(courseGroupDTO, CourseGroup.class);
    }

    private CourseGroupDTO convertToDTO(CourseGroup courseGroup){
        return modelMapper.map(courseGroup, CourseGroupDTO.class);
    }
}

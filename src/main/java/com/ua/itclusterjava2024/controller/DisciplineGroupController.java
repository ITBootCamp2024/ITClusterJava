package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.CourseGroupDTO;
import com.ua.itclusterjava2024.entity.DisciplineGroup;
import com.ua.itclusterjava2024.exceptions.ValidationException;
import com.ua.itclusterjava2024.service.interfaces.DisciplineGroupService;
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
public class DisciplineGroupController {
    private final DisciplineGroupService disciplineGroupService;
    private final ModelMapper modelMapper;
    private final CourseGroupValidator courseGroupValidator;

    public DisciplineGroupController(DisciplineGroupService disciplineGroupService, ModelMapper modelMapper, CourseGroupValidator courseGroupValidator) {
        this.disciplineGroupService = disciplineGroupService;
        this.modelMapper = modelMapper;
        this.courseGroupValidator = courseGroupValidator;
    }

    @GetMapping
    public List<CourseGroupDTO> showCourseGroupesList(){
        return disciplineGroupService.getAll().stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public RedirectView saveCourseGroup(@RequestBody @Valid CourseGroupDTO courseGroupDTO,
                                        BindingResult bindingResult){
        courseGroupValidator.validate(courseGroupDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        disciplineGroupService.create(convertToEntity(courseGroupDTO));
        return new RedirectView("/course_groupes");
    }

    @PatchMapping("/{id}")
    public RedirectView updateCourseGroup(@PathVariable("id") Long id,
                                           @RequestBody @Valid CourseGroupDTO courseGroupDTO,
                                          BindingResult bindingResult){
        courseGroupValidator.validate(courseGroupDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        disciplineGroupService.update(id, convertToEntity(courseGroupDTO));
        return new RedirectView("/course_groupes");
    }

    @GetMapping("/{id}")
    public CourseGroupDTO findById(@PathVariable Long id){
        return convertToDTO(disciplineGroupService.readById(id).orElse(null));
    }

    @DeleteMapping("/{id}")
    public RedirectView deleteCourseGroup(@PathVariable Long id){
        disciplineGroupService.delete(id);
        return new RedirectView("/course_groupes");
    }

    private DisciplineGroup convertToEntity(CourseGroupDTO courseGroupDTO){
        return modelMapper.map(courseGroupDTO, DisciplineGroup.class);
    }

    private CourseGroupDTO convertToDTO(DisciplineGroup disciplineGroup){
        return modelMapper.map(disciplineGroup, CourseGroupDTO.class);
    }
}

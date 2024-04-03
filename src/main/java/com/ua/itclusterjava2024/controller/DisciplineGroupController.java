package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.DisciplineGroupsDTO;
import com.ua.itclusterjava2024.entity.DisciplineGroups;
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
@RequestMapping("/discipline-groups")
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
    public List<DisciplineGroupsDTO> showCourseGroupesList(){
        return disciplineGroupService.getAll().stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public RedirectView saveCourseGroup(@RequestBody @Valid DisciplineGroupsDTO disciplineGroupsDTO,
                                        BindingResult bindingResult){
        courseGroupValidator.validate(disciplineGroupsDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        disciplineGroupService.create(convertToEntity(disciplineGroupsDTO));
        return new RedirectView("/discipline-groups");
    }

    @PatchMapping("/{id}")
    public RedirectView updateCourseGroup(@PathVariable("id") Long id,
                                           @RequestBody @Valid DisciplineGroupsDTO disciplineGroupsDTO,
                                          BindingResult bindingResult){
        courseGroupValidator.validate(disciplineGroupsDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        disciplineGroupService.update(id, convertToEntity(disciplineGroupsDTO));
        return new RedirectView("/discipline-groups");
    }

    @GetMapping("/{id}")
    public DisciplineGroupsDTO findById(@PathVariable Long id){
        return convertToDTO(disciplineGroupService.readById(id).orElse(null));
    }

    @DeleteMapping("/{id}")
    public RedirectView deleteCourseGroup(@PathVariable Long id){
        disciplineGroupService.delete(id);
        return new RedirectView("/discipline-groups");
    }

    private DisciplineGroups convertToEntity(DisciplineGroupsDTO disciplineGroupsDTO){
        return modelMapper.map(disciplineGroupsDTO, DisciplineGroups.class);
    }

    private DisciplineGroupsDTO convertToDTO(DisciplineGroups disciplineGroups){
        return modelMapper.map(disciplineGroups, DisciplineGroupsDTO.class);
    }
}

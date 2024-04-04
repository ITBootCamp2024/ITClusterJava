package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.*;
import com.ua.itclusterjava2024.entity.Department;
import com.ua.itclusterjava2024.entity.EducationLevel;
import com.ua.itclusterjava2024.entity.Position;
import com.ua.itclusterjava2024.exceptions.NotFoundException;
import com.ua.itclusterjava2024.service.implementation.*;
import com.ua.itclusterjava2024.service.interfaces.*;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import com.ua.itclusterjava2024.entity.Teachers;
import com.ua.itclusterjava2024.service.implementation.TeachersServiceImpl;
import com.ua.itclusterjava2024.validators.TeachersValidator;
import com.ua.itclusterjava2024.wrappers.Patcher;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/teachers")
public class TeachersController {

    private final TeachersService teachersService;
    private final EducationLevelsService educationLevel;
    private final PositionService positionService;
    private final DepartmentService departmentService;
    private final UniversityService universityService;
    private final ModelMapper modelMapper;
    @Autowired
    Patcher patcher;
    private final TeachersValidator teachersValidator;

    @Autowired
    public TeachersController(TeachersServiceImpl teachersService, EducationLevelsService educationLevelsService, PositionServiceImpl positionService, DepartmentServiceImpl departmentService, UniversityServiceImpl universityService, ModelMapper modelMapper, TeachersValidator teachersValidator) {
        this.teachersService = teachersService;
        this.educationLevel = educationLevelsService;
        this.positionService = positionService;
        this.departmentService = departmentService;
        this.universityService = universityService;
        this.modelMapper = modelMapper;
        this.teachersValidator = teachersValidator;
    }

    @GetMapping
    public PageWrapper<TeachersDTO> findAll(@RequestParam(defaultValue = "1") int page) {
        int pageSize = 20;
        PageRequest pageable = PageRequest.of(page - 1, pageSize);
        Page<TeachersDTO> teachersPage = teachersService.getAll(pageable).map(teachers -> convertToDTO(teachers));

        PageWrapper<TeachersDTO> pageWrapper = new PageWrapper<>();
        pageWrapper.setContent(teachersPage.getContent());
        pageWrapper.setPageNumber(teachersPage.getNumber());
        pageWrapper.setTotalElements(teachersPage.getTotalElements());
        return pageWrapper;
    }

    @PatchMapping("/{id}")
    public PageWrapper<TeachersDTO> updateEntity(@PathVariable("id") Long id, @RequestBody Teachers teachers) {
        Teachers existingTeacher = teachersService.readById(id).orElse(null);
        try {
            patcher.patch(existingTeacher, teachers);
            teachersService.create(existingTeacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return findAll(1);
    }


    @DeleteMapping("/{id}")
    public PageWrapper<TeachersDTO> delete(@PathVariable long id) {
        teachersService.delete(id);
        return findAll(1);
    }

    @GetMapping("/{id}")
    public TeachersDTO findById(@PathVariable long id) {
        return convertToDTO(teachersService.readById(id).orElse(null));
    }

    @PostMapping
    public PageWrapper<TeachersDTO> save(@RequestBody TeachersDTO teachers, BindingResult bindingResult) {
        teachersService.create(convertToEntity(teachers));
        return findAll(1);
    }

    public Teachers convertToEntity(TeachersDTO dto) {
        Teachers teacher = modelMapper.map(dto, Teachers.class);
        Position position = positionService.readById(dto.getPosition().getId()).orElseThrow();
        EducationLevel educationLevel = this.educationLevel.readById(dto.getEducation_level().getId())
                .orElseThrow(() -> new NotFoundException("Degree not found"));
        Department department = departmentService.readById(dto.getDepartment().getId())
                .orElseThrow(() -> new NotFoundException("Department not found"));
        teacher.setPosition(position);
        teacher.setEducation_level(educationLevel);
        teacher.setDepartment(department);
        return teacher;
    }

    public TeachersDTO convertToDTO(Teachers teacher) {
        TeachersDTO dto = modelMapper.map(teacher, TeachersDTO.class);
        dto.setPosition(PositionDTO.builder().id(teacher.getPosition().getId()).name(teacher.getPosition().getName()).build());
        dto.setEducation_level(EducationLevelDTO.builder().id(teacher.getEducation_level().getId()).name(teacher.getEducation_level().getName()).build());
        dto.setDepartment(DepartmentDTO.builder().id(teacher.getDepartment().getId())
                .name(teacher.getDepartment().getName())
                .build());
        dto.setUniversity(UniversityDTO.builder()
                .id(teacher.getDepartment().getUniversity().getId())
                .name(teacher.getDepartment().getUniversity().getName())
                .build());
        return dto;
    }

}



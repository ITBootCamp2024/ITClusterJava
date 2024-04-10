package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.*;
import com.ua.itclusterjava2024.entity.*;
import com.ua.itclusterjava2024.exceptions.NotFoundException;
import com.ua.itclusterjava2024.service.implementation.*;
import com.ua.itclusterjava2024.service.interfaces.*;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import com.ua.itclusterjava2024.service.implementation.TeachersServiceImpl;
import com.ua.itclusterjava2024.validators.TeachersValidator;
import com.ua.itclusterjava2024.wrappers.Patcher;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/teachers")
public class TeachersController {

    private final TeachersService teachersService;
    private final EducationLevelsService educationLevelService;
    private final PositionService positionService;
    private final DepartmentService departmentService;
    private final UniversityService universityService;
    private final ServiceInfoService serviceInfoService;
    private final ModelMapper modelMapper;
    @Autowired
    Patcher patcher;
    private final TeachersValidator teachersValidator;

    @Autowired
    public TeachersController(TeachersServiceImpl teachersService, EducationLevelsService educationLevelsService, PositionServiceImpl positionService, DepartmentServiceImpl departmentService, UniversityServiceImpl universityService, ServiceInfoService serviceInfoService, ModelMapper modelMapper, TeachersValidator teachersValidator) {
        this.teachersService = teachersService;
        this.educationLevelService = educationLevelsService;
        this.positionService = positionService;
        this.departmentService = departmentService;
        this.universityService = universityService;
        this.serviceInfoService = serviceInfoService;
        this.modelMapper = modelMapper;
        this.teachersValidator = teachersValidator;
    }

    @GetMapping
    public PageWrapper<TeachersDTO> findAll() {
        List<TeachersDTO> teachers = teachersService.getAll().stream().map(this::convertToDTO).toList();
        List<PositionDTO> positions = positionService.getAll().stream().map(position -> PositionDTO.builder().id(position.getId()).name(position.getName()).build()).toList();
        List<EducationLevelDTO> educationLevels = educationLevelService.getAll().stream().map(level -> EducationLevelDTO.builder().id(level.getId()).education_level(level.getEducation_level()).name(level.getName()).build()).toList();
        List<UniversityDTO> universitiesDTO = new ArrayList<>();
        List<University> universities = serviceInfoService.getAllUniversities();
        universities.forEach(university -> {
            List<Department> departments = serviceInfoService.getAllDepartmentsByUniversityId(university.getId());
            List<DepartmentDTO> departmentsDTO = departments.stream()
                    .map(department -> DepartmentDTO.builder().id(department.getId()).name(department.getName()).build())
                    .toList();

            universitiesDTO.add(UniversityDTO.builder().id(university.getId()).name(university.getName()).abbr(university.getAbbr()).department(departmentsDTO).build());
        });
        PageWrapper<TeachersDTO> pageWrapper = new PageWrapper<>();
        pageWrapper.setContent(teachers);
        pageWrapper.setService_info(ServiceInfoDTO.builder().position(positions).education_levels(educationLevels).university(universitiesDTO).build());
        pageWrapper.setTotalElements(teachers.size());
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
        findAll();
        return findAll();
    }


    @DeleteMapping("/{id}")
    public PageWrapper<TeachersDTO> delete(@PathVariable long id) {
        teachersService.delete(id);
        return findAll();
    }

    @GetMapping("/{id}")
    public TeachersDTO findById(@PathVariable long id) {
        return convertToDTO(teachersService.readById(id).orElse(null));
    }

    @PostMapping
    public PageWrapper<TeachersDTO> save(@RequestBody TeachersDTO teachers, BindingResult bindingResult) {
        teachersService.create(convertToEntity(teachers));
        return findAll();
    }

    public Teachers convertToEntity(TeachersDTO dto) {
        Teachers teacher = modelMapper.map(dto, Teachers.class);
        Position position = positionService.readById(dto.getPosition().getId()).orElseThrow();
        EducationLevel educationLevel = this.educationLevelService.readById(dto.getEducation_level().getId())
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



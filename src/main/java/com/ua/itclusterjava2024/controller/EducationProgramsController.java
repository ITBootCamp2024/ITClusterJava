package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.*;
import com.ua.itclusterjava2024.entity.Department;
import com.ua.itclusterjava2024.entity.EducationLevel;
import com.ua.itclusterjava2024.entity.EducationPrograms;
import com.ua.itclusterjava2024.entity.University;
import com.ua.itclusterjava2024.exceptions.NotFoundException;
import com.ua.itclusterjava2024.service.implementation.ServiceInfoService;
import com.ua.itclusterjava2024.service.interfaces.DepartmentService;
import com.ua.itclusterjava2024.service.interfaces.EducationLevelsService;
import com.ua.itclusterjava2024.service.interfaces.EducationProgramsService;

import com.ua.itclusterjava2024.service.interfaces.SpecialtyService;
import com.ua.itclusterjava2024.validators.ProgramsValidator;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import com.ua.itclusterjava2024.wrappers.Patcher;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/education-programs")
public class EducationProgramsController {
    private final EducationProgramsService educationProgramsService;
    private final SpecialtyService specialtyService;
    private final ServiceInfoService serviceInfoService;
    private final ModelMapper modelMapper;
    private final ProgramsValidator programsValidator;
    @Autowired
    Patcher patcher;
    private EducationLevelsService educationLevelService;
    private DepartmentService departmentService;

    @Autowired
    public EducationProgramsController(EducationProgramsService educationProgramsService, SpecialtyService specialtyService, ServiceInfoService serviceInfoService, ModelMapper modelMapper, ProgramsValidator programsValidator, EducationLevelsService educationLevelService, DepartmentService departmentService) {
        this.educationProgramsService = educationProgramsService;
        this.specialtyService = specialtyService;
        this.serviceInfoService = serviceInfoService;
        this.modelMapper = modelMapper;
        this.programsValidator = programsValidator;
        this.educationLevelService = educationLevelService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public PageWrapper<EducationProgramsDTO> findAll() {
        List<EducationProgramsDTO> educationProgramsPage = educationProgramsService.getAll().stream().map(this::convertToDTO).toList();
        List<SpecialtyDTO> specialties = specialtyService.getAll().stream().map(specialty -> SpecialtyDTO.builder().id(specialty.getId()).code(specialty.getCode()).name(specialty.getName()).build()).toList();
        List<UniversityDTO> universitiesDTO = new ArrayList<>();
        List<University> universities = serviceInfoService.getAllUniversities();
        universities.forEach(university -> {
            List<Department> departments = serviceInfoService.getAllDepartmentsByUniversityId(university.getId());
            List<DepartmentDTO> departmentsDTO = departments.stream()
                    .map(department -> DepartmentDTO.builder().id(department.getId()).name(department.getName()).build())
                    .toList();

            universitiesDTO.add(UniversityDTO.builder().id(university.getId()).name(university.getName()).abbr(university.getAbbr()).department(departmentsDTO).build());
        });
        List<EducationLevelDTO> educationLevels = educationLevelService.getAll().stream().map(level -> EducationLevelDTO.builder().id(level.getId()).education_level(level.getEducation_level()).name(level.getName()).build()).toList();

        PageWrapper<EducationProgramsDTO> pageWrapper = new PageWrapper<>();
        pageWrapper.setContent(educationProgramsPage);
        pageWrapper.setService_info(ServiceInfoDTO.builder().specialty(specialties).university(universitiesDTO).education_levels(educationLevels).build());
        pageWrapper.setTotalElements(educationProgramsPage.size());
        return pageWrapper;
    }

    @GetMapping("/{id}")
    public EducationProgramsDTO findById(@PathVariable long id) {
        return convertToDTO(educationProgramsService.readById(id).orElse(null));
    }

    @PostMapping
    public PageWrapper<EducationProgramsDTO> save(@RequestBody EducationProgramsDTO educationProgramsDTO, BindingResult bindingResult) {
        educationProgramsService.create(convertToEntity(educationProgramsDTO));
        return findAll();
    }

    @PatchMapping("/{id}")
    public PageWrapper<EducationProgramsDTO> update(@PathVariable("id") Long id,
                                                    @RequestBody EducationPrograms educationPrograms
    ) {
        EducationPrograms existingTeacher = educationProgramsService.readById(id).orElse(null);
        try {
            patcher.patch(existingTeacher, educationPrograms);
            educationProgramsService.create(existingTeacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return findAll();
    }

    @DeleteMapping("/{id}")
    public PageWrapper<EducationProgramsDTO> delete(@PathVariable long id) {
        educationProgramsService.delete(id);
        return findAll();
    }

    private EducationPrograms convertToEntity(EducationProgramsDTO dto) {
        EducationPrograms educationPrograms = modelMapper.map(dto, EducationPrograms.class);

        EducationLevel educationLevel = educationLevelService.readById(dto.getEducation_level().getId())
                .orElseThrow(() -> new NotFoundException("Education Level not found"));
        Department department = departmentService.readById(dto.getDepartment().getId())
                .orElseThrow(() -> new NotFoundException("Department not found"));

        educationPrograms.setEducation_level(educationLevel);
        educationPrograms.setDepartment(department);

        return educationPrograms;
    }

    public EducationProgramsDTO convertToDTO(EducationPrograms educationPrograms) {
        EducationProgramsDTO dto = modelMapper.map(educationPrograms, EducationProgramsDTO.class);
        dto.setSpecialty(SpecialtyDTO.builder().id(educationPrograms.getSpecialty().getId())
                .code(educationPrograms.getSpecialty().getCode()).build());
        dto.setEducation_level(EducationLevelDTO.builder()
                .id(educationPrograms.getEducation_level().getId())
                .education_level(educationPrograms.getEducation_level().getEducation_level())
                .name(educationPrograms.getEducation_level().getName())
                .build());
        dto.setUniversity(UniversityDTO.builder().id(educationPrograms.getDepartment().getUniversity().getId())
                .name(educationPrograms.getDepartment().getUniversity().getName()).build());
        dto.setDepartment(DepartmentDTO.builder()
                .id(educationPrograms.getDepartment().getId())
                .url(educationPrograms.getDepartment().getUrl())
                .name(educationPrograms.getDepartment().getName())
                .build());
        return dto;
    }
}

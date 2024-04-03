package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.*;
import com.ua.itclusterjava2024.entity.Department;
import com.ua.itclusterjava2024.entity.EducationLevel;
import com.ua.itclusterjava2024.entity.EducationPrograms;
import com.ua.itclusterjava2024.exceptions.NotFoundException;
import com.ua.itclusterjava2024.service.interfaces.DepartmentService;
import com.ua.itclusterjava2024.service.interfaces.EducationLevelsService;
import com.ua.itclusterjava2024.service.interfaces.EducationProgramsService;

import com.ua.itclusterjava2024.validators.ProgramsValidator;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import com.ua.itclusterjava2024.wrappers.Patcher;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/education-programs")
public class EducationProgramsController {
    private final EducationProgramsService educationProgramsService;
    private final ModelMapper modelMapper;
    private final ProgramsValidator programsValidator;
    @Autowired
    Patcher patcher;
    private EducationLevelsService educationLevelService;
    private DepartmentService departmentService;

    @Autowired
    public EducationProgramsController(EducationProgramsService educationProgramsService, ModelMapper modelMapper, ProgramsValidator programsValidator, EducationLevelsService educationLevelService, DepartmentService departmentService) {
        this.educationProgramsService = educationProgramsService;
        this.modelMapper = modelMapper;
        this.programsValidator = programsValidator;
        this.educationLevelService = educationLevelService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public PageWrapper<EducationProgramsDTO> findAll(@RequestParam(defaultValue = "1") int page) {
        int pageSize = 20;
        PageRequest pageable = PageRequest.of(page - 1, pageSize);
        Page<EducationProgramsDTO> educationProgramsPage = educationProgramsService.getAll(pageable).map(this::convertToDTO);

        PageWrapper<EducationProgramsDTO> pageWrapper = new PageWrapper<>();
        pageWrapper.setContent(educationProgramsPage.getContent());
        pageWrapper.setPageNumber(educationProgramsPage.getNumber());
        pageWrapper.setTotalElements(educationProgramsPage.getTotalElements());
        return pageWrapper;
    }

    @GetMapping("/{id}")
    public EducationProgramsDTO findById(@PathVariable long id) {
        return convertToDTO(educationProgramsService.readById(id).orElse(null));
    }

    @PostMapping
    public PageWrapper<EducationProgramsDTO> save(@RequestBody EducationProgramsDTO educationProgramsDTO, BindingResult bindingResult) {
        educationProgramsService.create(convertToEntity(educationProgramsDTO));
        return findAll(1);
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
        return findAll(1);
    }

    @DeleteMapping("/{id}")
    public PageWrapper<EducationProgramsDTO> delete(@PathVariable long id) {
        educationProgramsService.delete(id);
        return findAll(1);
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

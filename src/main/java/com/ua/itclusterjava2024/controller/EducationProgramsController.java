package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.*;
import com.ua.itclusterjava2024.entity.*;
import com.ua.itclusterjava2024.exceptions.NotFoundException;
import com.ua.itclusterjava2024.service.implementation.ServiceInfoService;
import com.ua.itclusterjava2024.service.interfaces.EducationProgramsService;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import com.ua.itclusterjava2024.wrappers.Patcher;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/education-programs")
public class EducationProgramsController {
    private final EducationProgramsService educationProgramsService;
    private final ServiceInfoService serviceInfoService;
    private final ModelMapper modelMapper;
    private final Patcher<EducationPrograms> patcher;
    private final EntityManager entityManager;

    @Autowired
    public EducationProgramsController(EducationProgramsService educationProgramsService, ServiceInfoService serviceInfoService, ModelMapper modelMapper, Patcher<EducationPrograms> patcher, EntityManager entityManager) {
        this.educationProgramsService = educationProgramsService;
        this.serviceInfoService = serviceInfoService;
        this.modelMapper = modelMapper;
        this.patcher = patcher;
        this.entityManager = entityManager;
    }

    @GetMapping
    public PageWrapper<EducationProgramsDTO> findAll() {
        List<EducationProgramsDTO> programsDTOS = educationProgramsService.getAll()
                .stream().map(this::convertToDTO).toList();

        ServiceInfoDTO serviceInfo = prepareServiceInfo();

        PageWrapper<EducationProgramsDTO> pageWrapper = new PageWrapper<>();
        pageWrapper.setContent(programsDTOS);
        pageWrapper.setServiceInfo(serviceInfo);
        pageWrapper.setTotalElements(programsDTOS.size());
        return pageWrapper;
    }

    @GetMapping("/{id}")
    public EducationProgramsDTO findById(@PathVariable long id) {
        return convertToDTO(educationProgramsService.readById(id).orElse(null));
    }

    @PostMapping
    public PageWrapper<EducationProgramsDTO> save(@RequestBody EducationProgramsDTO educationProgramsDTO) {
        educationProgramsService.create(convertToEntity(educationProgramsDTO));
        entityManager.clear();
        return findAll();
    }

    @PatchMapping("/{id}")
    public PageWrapper<EducationProgramsDTO> update(@PathVariable("id") Long id,
                                                    @RequestBody EducationPrograms educationPrograms) {
        EducationPrograms existingTeacher = educationProgramsService.readById(id)
                .orElseThrow(() -> new NotFoundException("Education program not found with id: " + id));
        try {
            patcher.patch(existingTeacher, educationPrograms);
            educationProgramsService.update(id, existingTeacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
        entityManager.clear();
        return findAll();
    }

    @DeleteMapping("/{id}")
    public PageWrapper<EducationProgramsDTO> delete(@PathVariable long id) {
        educationProgramsService.delete(id);
        return findAll();
    }

    private EducationPrograms convertToEntity(EducationProgramsDTO dto) {
        EducationPrograms educationPrograms = modelMapper.map(dto, EducationPrograms.class);

        if (dto.getSpecialty() != null)
            educationPrograms.setSpecialty(modelMapper.map(dto.getSpecialty(), Specialty.class));

        if (dto.getDepartment() != null) {
            educationPrograms.setDepartment(modelMapper.map(dto.getDepartment(), Department.class));
            if (dto.getUniversity() != null)
                educationPrograms.getDepartment().setUniversity(modelMapper.map(dto.getUniversity(), University.class));
        }

        if (dto.getEducationLevel() != null)
            educationPrograms.setEducationLevel(modelMapper.map(dto.getEducationLevel(), EducationLevel.class));

        return educationPrograms;
    }

    public EducationProgramsDTO convertToDTO(EducationPrograms educationPrograms) {
        EducationProgramsDTO dto = modelMapper.map(educationPrograms, EducationProgramsDTO.class);
        dto.setSpecialty(SpecialtyDTO.builder().id(educationPrograms.getSpecialty().getId())
                .code(educationPrograms.getSpecialty().getCode()).build());
        dto.setEducationLevel(EducationLevelDTO.builder()
                .id(educationPrograms.getEducationLevel().getId())
                .educationLevel(educationPrograms.getEducationLevel().getEducationLevel())
                .name(educationPrograms.getEducationLevel().getName())
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

    private ServiceInfoDTO prepareServiceInfo() {
        ServiceInfoDTO serviceInfo = new ServiceInfoDTO();
        serviceInfo.setSpecialty(prepareSpecialties());
        serviceInfo.setUniversity(prepareUniversities());
        serviceInfo.setEducationLevels(prepareEducationLevels());
        return serviceInfo;
    }

    private List<EducationLevelDTO> prepareEducationLevels() {
        return serviceInfoService.getAllEducationLevels().stream().map(educationLevel -> EducationLevelDTO.builder()
                .id(educationLevel.getId())
                .educationLevel(educationLevel.getEducationLevel())
                .name(educationLevel.getName())
                .build()).toList();
    }

    private List<UniversityDTO> prepareUniversities() {
        return serviceInfoService.getAllUniversities().stream().map(university -> UniversityDTO.builder()
                .id(university.getId())
                .name(university.getName())
                .abbr(university.getAbbr())
                .department(prepareDepartments(university.getId()))
                .build()).toList();
    }

    private List<DepartmentDTO> prepareDepartments(Long id) {
        return serviceInfoService.getAllDepartmentsByUniversityId(id).stream().map(department -> DepartmentDTO.builder()
                .id(department.getId())
                .name(department.getName())
                .build()).toList();
    }

    private List<SpecialtyDTO> prepareSpecialties() {
        return serviceInfoService.getAllSpecialties().stream().map(specialty -> SpecialtyDTO.builder()
                .id(specialty.getId())
                .code(specialty.getCode())
                .name(specialty.getName())
                .build()).toList();
    }
}

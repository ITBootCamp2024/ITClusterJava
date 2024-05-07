package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.*;
import com.ua.itclusterjava2024.entity.*;
import com.ua.itclusterjava2024.service.implementation.ServiceInfoService;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/service_info")
public class ServiceInfoController {
    private final ServiceInfoService serviceInfoService;
    private final ModelMapper modelMapper;
    private static final int PAGE_SIZE = 20;

    @Autowired
    public ServiceInfoController(ServiceInfoService serviceInfoService, ModelMapper modelMapper) {
        this.serviceInfoService = serviceInfoService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public PageWrapper<ServiceInfoDTO> findAll(@RequestParam(defaultValue = "1") int page) {
        PageRequest pageable = PageRequest.of(page - 1, PAGE_SIZE);
        Page<ServiceInfoDTO> pageOfServiceInfoDTO = createPage(pageable);
        return new PageWrapper<>(pageOfServiceInfoDTO.getContent(), pageOfServiceInfoDTO.getTotalElements());
    }

    private Page<ServiceInfoDTO> createPage(Pageable pageable) {
        ServiceInfoDTO serviceInfoDTO = buildServiceInfoDTO();
        List<ServiceInfoDTO> content = Collections.singletonList(serviceInfoDTO);
        return new PageImpl<>(content, pageable, content.size());
    }

    private ServiceInfoDTO buildServiceInfoDTO() {
        return ServiceInfoDTO.builder()
                .position(serviceInfoService.getAllPositions().stream().map(this::convertPositionToDTO).toList())
                .educationLevels(serviceInfoService.getAllEducationLevels().stream().map(this::convertEducationLevelToDTO).toList())
                .teachers(serviceInfoService.getAllTeachers().stream().map(this::convertTeacherToDTO).toList())
                .university(buildUniversityDTO())
                .specialty(serviceInfoService.getAllSpecialties().stream().map(this::convertSpecialtyToDTO).toList())
                .educationProgram(serviceInfoService.getAllEducationPrograms().stream().map(this::convertEducationProgramToDTO).toList())
                .discipline(serviceInfoService.getAllDisciplines().stream().map(this::convertDisciplineToDTO).toList())
                .disciplineBlocks(buildDisciplineBlocksDTO())
                .build();
    }

    private List<UniversityDTO> buildUniversityDTO() {
        return serviceInfoService.getAllUniversities().stream()
                .map(this::convertUniversityToDTO)
                .toList();
    }

    private List<DisciplineBlocksDTO> buildDisciplineBlocksDTO() {
        return serviceInfoService.getAllDisciplineBlocks().stream()
                .map(this::convertDisciplineBlockToDTO)
                .toList();
    }

    private PositionDTO convertPositionToDTO(Position position) {
        return modelMapper.map(position, PositionDTO.class);
    }

    private EducationLevelDTO convertEducationLevelToDTO(EducationLevel educationLevel) {
        return modelMapper.map(educationLevel, EducationLevelDTO.class);
    }

    private TeachersDTO convertTeacherToDTO(Teachers teacher) {
        return TeachersDTO.builder().id(teacher.getId()).name(teacher.getName()).build();
    }

    private UniversityDTO convertUniversityToDTO(University university) {
        List<DepartmentDTO> departmentsDTO = serviceInfoService.getAllDepartmentsByUniversityId(university.getId()).stream()
                .map(department -> DepartmentDTO.builder().id(department.getId()).name(department.getName()).build())
                .toList();

        return UniversityDTO.builder()
                .id(university.getId())
                .name(university.getName())
                .abbr(university.getAbbr())
                .department(departmentsDTO)
                .build();
    }

    private SpecialtyDTO convertSpecialtyToDTO(Specialty specialty) {
        return SpecialtyDTO.builder().id(specialty.getId()).code(specialty.getCode()).name(specialty.getName()).build();
    }

    private EducationProgramsDTO convertEducationProgramToDTO(EducationPrograms educationPrograms) {
        return EducationProgramsDTO.builder().id(educationPrograms.getId()).name(educationPrograms.getName()).programUrl(educationPrograms.getProgramUrl()).build();
    }

    private DisciplinesDTO convertDisciplineToDTO(Disciplines discipline) {
        return DisciplinesDTO.builder().id(discipline.getId()).name(discipline.getName()).build();
    }

    private DisciplineBlocksDTO convertDisciplineBlockToDTO(DisciplineBlocks disciplineBlock) {
        List<DisciplineGroupsDTO> disciplineGroupsDTO = serviceInfoService.getAllDisciplineGroupsByDisciplineBlocksId(disciplineBlock).stream()
                .map(disciplineGroups -> DisciplineGroupsDTO.builder().id(disciplineGroups.getId()).name(disciplineGroups.getName()).build())
                .toList();

        return DisciplineBlocksDTO.builder()
                .id(disciplineBlock.getId())
                .name(disciplineBlock.getName())
                .disciplineGroups(disciplineGroupsDTO)
                .build();
    }
}

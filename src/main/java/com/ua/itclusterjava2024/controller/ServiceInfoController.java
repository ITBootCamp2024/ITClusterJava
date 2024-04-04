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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/service_info")
public class ServiceInfoController {
    private final ServiceInfoService serviceInfoService;
    private final ModelMapper modelMapper;
    @Autowired
    public ServiceInfoController(ServiceInfoService serviceInfoService, ModelMapper modelMapper) {
        this.serviceInfoService = serviceInfoService;
        this.modelMapper = modelMapper;
    }
    @GetMapping
    public PageWrapper<ServiceInfoDTO> findAll(@RequestParam(defaultValue = "1") int page) {
        int pageSize = 20;
        PageRequest pageable = PageRequest.of(page - 1, pageSize);

        Page<ServiceInfoDTO> pageOfServiceInfoDTO = createPage(pageable);

        PageWrapper<ServiceInfoDTO> pageWrapper = new PageWrapper<>();
        pageWrapper.setContent(pageOfServiceInfoDTO.getContent());
        pageWrapper.setPageNumber(pageOfServiceInfoDTO.getNumber());
        pageWrapper.setTotalElements(pageOfServiceInfoDTO.getTotalElements());
        return pageWrapper;
    }

    private Page<ServiceInfoDTO> createPage(Pageable pageable) {
        List<UniversityDTO> universitiesDTO = new ArrayList<>();

        List<University> universities = serviceInfoService.getAllUniversities();
        universities.forEach(university -> {
            List<Department> departments = serviceInfoService.getAllDepartmentsByUniversityId(university.getId());
            List<DepartmentDTO> departmentsDTO = departments.stream()
                    .map(department -> DepartmentDTO.builder().id(department.getId()).name(department.getName()).build())
                    .collect(Collectors.toList());

            universitiesDTO.add(UniversityDTO.builder().id(university.getId()).name(university.getName()).abbr(university.getAbbr()).department(departmentsDTO).build());
        });

        List<DisciplineBlocksDTO> disciplineBlocksDTO = new ArrayList<>();
        List<DisciplineBlocks> disciplineBlocks = serviceInfoService.getAllDisciplineBlocks();
        disciplineBlocks.forEach(disciplineBlock -> {
            List<DisciplineGroups> disciplineGroup = serviceInfoService.getAllDisciplineGroupsByDisciplineBlocksId(disciplineBlock);
            List<DisciplineGroupsDTO> disciplineGroupsDTO = disciplineGroup.stream()
                    .map(disciplineGroups -> DisciplineGroupsDTO.builder().id(disciplineGroups.getId()).name(disciplineGroups.getName()).build())
                    .collect(Collectors.toList());

            disciplineBlocksDTO.add(DisciplineBlocksDTO.builder().id(disciplineBlock.getId()).name(disciplineBlock.getName()).disciplineGroups(disciplineGroupsDTO).build());
        });

        ServiceInfoDTO serviceInfoDTO = ServiceInfoDTO.builder()
                .position(serviceInfoService.getAllPositions().stream().map(this::convertPositionToDTO).toList())
                .education_levels(serviceInfoService.getAllEducationLevels().stream().map(this::convertEducationLevelToDTO).toList())
                .teachers(serviceInfoService.getAllTeachers().stream().map(teachers -> TeachersDTO.builder().id(teachers.getId()).name(teachers.getName()).build()).toList())
                .university(universitiesDTO)
                .specialty(serviceInfoService.getAllSpecialties().stream().map(specialty -> SpecialtyDTO.builder().id(specialty.getId()).code(specialty.getCode()).name(specialty.getName()).build()).toList())
                .education_program(serviceInfoService.getAllEducationPrograms().stream().map(educationPrograms -> EducationProgramsDTO.builder().id(educationPrograms.getId()).name(educationPrograms.getName()).program_url(educationPrograms.getProgram_url()).build()).toList())
                .discipline(serviceInfoService.getAllDisciplines().stream().map(disciplines -> DisciplinesDTO.builder().id(disciplines.getId()).name(disciplines.getName()).build()).toList())
                .disciplineBlocks(disciplineBlocksDTO)
                .build();

        List<ServiceInfoDTO> content = Collections.singletonList(serviceInfoDTO);
        return new PageImpl<>(content, pageable, content.size());
    }

    private DepartmentDTO convertDepartmentToDTO(Department department){
        return modelMapper.map(department, DepartmentDTO.class);
    }
    private PositionDTO convertPositionToDTO(Position position){
        return modelMapper.map(position, PositionDTO.class);
    }
    private EducationLevelDTO convertEducationLevelToDTO(EducationLevel position){
        return modelMapper.map(position, EducationLevelDTO.class);
    }
    private TeachersDTO convertTeachersToDTO(Teachers position){
        return modelMapper.map(position, TeachersDTO.class);
    }
    private UniversityDTO convertUniversitiesToDTO(University position){
        return modelMapper.map(position, UniversityDTO.class);
    }
    private SpecialtyDTO convertSpecialtyToDTO(Specialty position){
        return modelMapper.map(position, SpecialtyDTO.class);
    }
    private DisciplinesDTO convertDisciplinesToDTO(Disciplines position){
        return modelMapper.map(position, DisciplinesDTO.class);
    }
    private DisciplineBlocksDTO convertDisciplineBlocksToDTO(DisciplineBlocks position){
        return modelMapper.map(position, DisciplineBlocksDTO.class);
    }
    private DisciplineGroupsDTO convertDisciplineGroupsToDTO(DisciplineGroups position){
        return modelMapper.map(position, DisciplineGroupsDTO.class);
    }

}

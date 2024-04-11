package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.*;
import com.ua.itclusterjava2024.entity.*;
import com.ua.itclusterjava2024.service.implementation.ServiceInfoService;
import com.ua.itclusterjava2024.service.interfaces.DisciplinesService;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import com.ua.itclusterjava2024.wrappers.Patcher;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplines")
public class DisciplineController {
    private final DisciplinesService disciplinesService;
    private final ServiceInfoService serviceInfoService;
    private final ModelMapper modelMapper;
    private final EntityManager entityManager;
    private final Patcher<Disciplines> patcher;

    @Autowired
    public DisciplineController(DisciplinesService disciplineService, ModelMapper modelMapper, ServiceInfoService serviceInfoService, EntityManager entityManager, Patcher<Disciplines> patcher) {
        this.disciplinesService = disciplineService;
        this.modelMapper = modelMapper;
        this.serviceInfoService = serviceInfoService;
        this.entityManager = entityManager;
        this.patcher = patcher;
    }

    @GetMapping
    public PageWrapper<DisciplinesDTO> findAll() {
        List<DisciplinesDTO> disciplines = disciplinesService.getAll().stream()
                .map(this::convertToDTO)
                .toList();

        ServiceInfoDTO serviceInfo = prepareServiceInfo();

        PageWrapper<DisciplinesDTO> pageWrapper = new PageWrapper<>();
        pageWrapper.setContent(disciplines);
        pageWrapper.setService_info(serviceInfo);
        pageWrapper.setTotalElements(disciplines.size());

        return pageWrapper;
    }


    @GetMapping("/{id}")
    public DisciplinesDTO findById(@PathVariable long id) {
        return convertToDTO(disciplinesService.readById(id).orElse(null));
    }

    @PostMapping
    public PageWrapper<DisciplinesDTO> save(@RequestBody @Valid DisciplinesDTO disciplinesDTO) {
        disciplinesService.create(convertToEntity(disciplinesDTO));
        entityManager.clear();
        return findAll();
    }

    @PatchMapping("/{id}")
    public PageWrapper<DisciplinesDTO> update(@PathVariable("id") Long id,
                                              @RequestBody DisciplinesDTO disciplinesDTO) {
        Disciplines existingDiscipline = disciplinesService.readById(id)

                .orElseThrow(() -> new RuntimeException("Discipline with id " + id + " not found"));
        Disciplines updatedDiscipline = convertToEntity(disciplinesDTO);
        try {
            patcher.patch(existingDiscipline, updatedDiscipline);
            disciplinesService.update(id, existingDiscipline);
        } catch (Exception e) {
            e.printStackTrace();
        }
        entityManager.clear();
        return findAll();
    }

    @DeleteMapping("/{id}")
    public PageWrapper<DisciplinesDTO> delete(@PathVariable long id) {
        disciplinesService.delete(id);
        return findAll();
    }

    private Disciplines convertToEntity(DisciplinesDTO dto) {
        Disciplines disciplines = modelMapper.map(dto, Disciplines.class);
        if (dto.getTeacher() != null) {
            disciplines.setTeachers(modelMapper.map(dto.getTeacher(), Teachers.class));
        }

        if (dto.getDiscipline_group() != null) {
            disciplines.setDiscipline_group(modelMapper.map(dto.getDiscipline_group(), DisciplineGroups.class));
            if (dto.getDiscipline_block() != null) {
                disciplines.getDiscipline_group()
                        .setBlock_id(modelMapper.map(dto.getDiscipline_block(), DisciplineBlocks.class));
            }
        }

        if (dto.getEducation_program() != null) {
            disciplines.setEducation_program(modelMapper.map(dto.getEducation_program(), EducationPrograms.class));
        }

        return disciplines;
    }

    private DisciplinesDTO convertToDTO(Disciplines disciplines) {
        DisciplinesDTO dto = modelMapper.map(disciplines, DisciplinesDTO.class);
        dto.setTeacher(TeachersDTO.builder().id(disciplines.getTeachers().getId())
                .name(disciplines.getTeachers().getName()).build());
        dto.setDiscipline_block(DisciplineBlocksDTO.builder().id(disciplines.getDiscipline_group().getBlock_id().getId())
                .name(disciplines.getDiscipline_group().getBlock_id().getName()).build());
        dto.setDiscipline_group(DisciplineGroupsDTO.builder().id(disciplines.getDiscipline_group().getId())
                .name(disciplines.getDiscipline_group().getName()).build());
        dto.setEducation_program(EducationProgramsDTO.builder().id(disciplines.getEducation_program().getId())
                .name(disciplines.getEducation_program().getName())
                .program_url(disciplines.getEducation_program().getProgram_url()).build());
        return dto;
    }

    private ServiceInfoDTO prepareServiceInfo() {
        ServiceInfoDTO serviceInfo = new ServiceInfoDTO();
        serviceInfo.setTeachers(prepareTeachers());
        serviceInfo.setEducation_program(prepareEducationPrograms());
        serviceInfo.setDisciplineBlocks(prepareDisciplineBlocks());
        return serviceInfo;
    }

    private List<TeachersDTO> prepareTeachers() {
        return serviceInfoService.getAllTeachers().stream()
                .map(teacher -> TeachersDTO.builder()
                        .id(teacher.getId())
                        .name(teacher.getName()).build())
                .toList();
    }

    private List<EducationProgramsDTO> prepareEducationPrograms() {
        return serviceInfoService.getAllEducationPrograms().stream()
                .map(program -> EducationProgramsDTO.builder()
                        .id(program.getId())
                        .name(program.getName())
                        .program_url(program.getProgram_url()).build())
                .toList();
    }

    private List<DisciplineBlocksDTO> prepareDisciplineBlocks() {
        return serviceInfoService.getAllDisciplineBlocks().stream()
                .map(block -> DisciplineBlocksDTO.builder()
                        .id(block.getId())
                        .name(block.getName())
                        .disciplineGroups(prepareDisciplineGroups(block))
                        .build())
                .toList();
    }

    private List<DisciplineGroupsDTO> prepareDisciplineGroups(DisciplineBlocks block) {
        return serviceInfoService.getAllDisciplineGroupsByDisciplineBlocksId(block).stream()
                .map(group -> DisciplineGroupsDTO.builder()
                        .id(group.getId())
                        .name(group.getName()).build())
                .toList();
    }
}

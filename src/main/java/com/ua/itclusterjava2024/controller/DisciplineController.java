package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.*;
import com.ua.itclusterjava2024.entity.*;
import com.ua.itclusterjava2024.service.implementation.ServiceInfoService;
import com.ua.itclusterjava2024.service.interfaces.DisciplinesService;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import com.ua.itclusterjava2024.wrappers.Patcher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
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
        pageWrapper.setServiceInfo(serviceInfo);
        pageWrapper.setTotalElements(disciplines.size());

        return pageWrapper;
    }


    @GetMapping("/{id}")
    public DisciplinesDTO findById(@PathVariable long id) {
        return convertToDTO(disciplinesService.readById(id).orElseThrow(() -> new EntityNotFoundException("Discipline with id " + id + " not found")));
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
                .orElseThrow(() -> new EntityNotFoundException("Discipline with id " + id + " not found"));

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

        if (dto.getDisciplineGroups() != null) {
            disciplines.setDisciplineGroups(modelMapper.map(dto.getDisciplineGroups(), DisciplineGroups.class));
            if (dto.getDisciplineBlock() != null) {
                disciplines.getDisciplineGroups()
                        .setDisciplineBlocks(modelMapper.map(dto.getDisciplineBlock(), DisciplineBlocks.class));
            }
        }

        if (dto.getEducationPrograms() != null) {
            disciplines.setEducationPrograms(modelMapper.map(dto.getEducationPrograms(), EducationPrograms.class));
        }

        return disciplines;
    }

    private DisciplinesDTO convertToDTO(Disciplines disciplines) {
        DisciplinesDTO dto = modelMapper.map(disciplines, DisciplinesDTO.class);
        dto.setTeacher(TeachersDTO.builder().id(disciplines.getTeachers().getId())
                .name(disciplines.getTeachers().getName()).build());
        dto.setDisciplineBlock(DisciplineBlocksDTO.builder().id(disciplines.getDisciplineGroups().getDisciplineBlocks().getId())
                .name(disciplines.getDisciplineGroups().getDisciplineBlocks().getName()).build());
        dto.setDisciplineGroups(DisciplineGroupsDTO.builder().id(disciplines.getDisciplineGroups().getId())
                .name(disciplines.getDisciplineGroups().getName()).build());
        dto.setEducationPrograms(EducationProgramsDTO.builder().id(disciplines.getEducationPrograms().getId())
                .name(disciplines.getEducationPrograms().getName())
                .programUrl(disciplines.getEducationPrograms().getProgramUrl()).build());
        return dto;
    }

    private ServiceInfoDTO prepareServiceInfo() {
        ServiceInfoDTO serviceInfo = new ServiceInfoDTO();
        serviceInfo.setTeachers(prepareTeachers());
        serviceInfo.setEducationProgram(prepareEducationPrograms());
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
                        .programUrl(program.getProgramUrl()).build())
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

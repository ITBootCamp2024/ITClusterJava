package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.*;
import com.ua.itclusterjava2024.entity.*;
import com.ua.itclusterjava2024.exceptions.ValidationException;
import com.ua.itclusterjava2024.service.interfaces.DisciplineBlocksService;
import com.ua.itclusterjava2024.service.interfaces.DisciplineGroupService;
import com.ua.itclusterjava2024.validators.CourseGroupValidator;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import com.ua.itclusterjava2024.wrappers.Patcher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discipline-groups")
public class DisciplineGroupController {
    private final DisciplineGroupService disciplineGroupService;
    private final DisciplineBlocksService disciplineBlocksService;
    private final ModelMapper modelMapper;
    private final CourseGroupValidator courseGroupValidator;
    private final Patcher<DisciplineGroups> patcher;
    private final EntityManager entityManager;

    @Autowired
    public DisciplineGroupController(DisciplineGroupService disciplineGroupService, DisciplineBlocksService disciplineBlocksService, ModelMapper modelMapper, CourseGroupValidator courseGroupValidator, Patcher<DisciplineGroups> patcher, EntityManager entityManager) {
        this.disciplineGroupService = disciplineGroupService;
        this.disciplineBlocksService = disciplineBlocksService;
        this.modelMapper = modelMapper;
        this.courseGroupValidator = courseGroupValidator;
        this.patcher = patcher;
        this.entityManager = entityManager;
    }

    @GetMapping
    public PageWrapper<DisciplineGroupsDTO> findAll() {
        List<DisciplineGroupsDTO> disciplineGroupsPage = disciplineGroupService.getAll().stream().map(this::convertToDTO).toList();

        List<DisciplineBlocksDTO> disciplineBlocks = disciplineBlocksService.getAll().stream().map(i -> DisciplineBlocksDTO.builder().id(i.getId()).name(i.getName()).build()).toList();
        PageWrapper<DisciplineGroupsDTO> pageWrapper = new PageWrapper<>();
        pageWrapper.setContent(disciplineGroupsPage);
        pageWrapper.setServiceInfo(ServiceInfoDTO.builder().disciplineBlocks(disciplineBlocks).build());
        pageWrapper.setTotalElements(disciplineGroupsPage.size());
        return pageWrapper;
    }

    @CrossOrigin
    @PostMapping
    public PageWrapper<DisciplineGroupsDTO> save(@RequestBody @Valid DisciplineGroupsDTO disciplineGroupsDTO,
                                                 BindingResult bindingResult) {
        courseGroupValidator.validate(disciplineGroupsDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        disciplineGroupService.create(convertToEntity(disciplineGroupsDTO));
        entityManager.clear();
        return findAll();
    }

    @CrossOrigin
    @PatchMapping("/{id}")
    public PageWrapper<DisciplineGroupsDTO> update(@PathVariable("id") Long id,
                                                   @RequestBody DisciplineGroupsDTO disciplineGroupsDTO) {
        DisciplineGroups existingDisciplineGroups = disciplineGroupService.readById(id)
                .orElseThrow(() -> new EntityNotFoundException("DisciplineGroups not found with id: " + id));
        DisciplineGroups updatedDisciplineGroupsDTO = convertToEntity(disciplineGroupsDTO);
        try {
            patcher.patch(existingDisciplineGroups, updatedDisciplineGroupsDTO);
            disciplineGroupService.update(id, existingDisciplineGroups);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        entityManager.clear();
        return findAll();
    }

    @GetMapping("/{id}")
    public DisciplineGroupsDTO findById(@PathVariable Long id) {
        return convertToDTO(disciplineGroupService.readById(id).orElseThrow(() -> new EntityNotFoundException("DisciplineGroups not found with id: " + id)));
    }

    @DeleteMapping("/{id}")
    public PageWrapper<DisciplineGroupsDTO> delete(@PathVariable Long id) {
        disciplineGroupService.delete(id);
        return findAll();
    }

    private DisciplineGroups convertToEntity(DisciplineGroupsDTO disciplineGroupsDTO) {
        DisciplineGroups disciplineGroups = modelMapper.map(disciplineGroupsDTO, DisciplineGroups.class);
        if (disciplineGroupsDTO.getBlock() != null) {
            disciplineGroups.setDisciplineBlocks(modelMapper.map(disciplineGroupsDTO.getBlock(), DisciplineBlocks.class));
        }
        return disciplineGroups;
    }

    private DisciplineGroupsDTO convertToDTO(DisciplineGroups disciplineGroups) {
        DisciplineGroupsDTO dto = modelMapper.map(disciplineGroups, DisciplineGroupsDTO.class);
        dto.setBlock(DisciplineBlocksDTO.builder()
                .id(disciplineGroups.getDisciplineBlocks().getId())
                .name(disciplineGroups.getDisciplineBlocks().getName()).build());
        return dto;
    }
}

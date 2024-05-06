package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.*;
import com.ua.itclusterjava2024.entity.*;
import com.ua.itclusterjava2024.service.implementation.ServiceInfoService;
import com.ua.itclusterjava2024.service.interfaces.TeachersService;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import com.ua.itclusterjava2024.wrappers.Patcher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/teachers")
public class TeachersController {

    private final TeachersService teachersService;
    private final ServiceInfoService serviceInfoService;
    private final ModelMapper modelMapper;
    private final EntityManager entityManager;
    private final Patcher<Teachers> patcher;

    @Autowired
    public TeachersController(TeachersService teachersService, ServiceInfoService serviceInfoService, ModelMapper modelMapper, EntityManager entityManager, Patcher<Teachers> patcher) {
        this.teachersService = teachersService;
        this.serviceInfoService = serviceInfoService;
        this.modelMapper = modelMapper;
        this.entityManager = entityManager;
        this.patcher = patcher;
    }

    @GetMapping
    public PageWrapper<TeachersDTO> findAll() {
        List<TeachersDTO> teachers = teachersService.getAll().stream()
                .map(this::convertToDTO).toList();

        PageWrapper<TeachersDTO> pageWrapper = new PageWrapper<>();
        pageWrapper.setContent(teachers);
        pageWrapper.setServiceInfo(prepareServiceInfo());
        pageWrapper.setTotalElements(teachers.size());
        return pageWrapper;
    }


    @PatchMapping("/{id}")
    public PageWrapper<TeachersDTO> updateEntity(@PathVariable("id") Long id, @RequestBody TeachersDTO teachersDTO) {
        Teachers existingTeacher = teachersService.readById(id).orElseThrow(() -> new EntityNotFoundException("Teacher with id " + id + " not found"));
        Teachers teachers = convertToEntity(teachersDTO);
        try {
            patcher.patch(existingTeacher, teachers);
            teachersService.update(id, existingTeacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
        entityManager.clear();
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
    public PageWrapper<TeachersDTO> save(@RequestBody TeachersDTO teachers) {
        teachersService.create(convertToEntity(teachers));
        entityManager.clear();
        return findAll();
    }

    public Teachers convertToEntity(TeachersDTO dto) {
        Teachers teacher = modelMapper.map(dto, Teachers.class);
        if (dto.getPosition() != null) {
            teacher.setPosition(modelMapper.map(dto.getPosition(), Position.class));
        }
        if (dto.getPosition() != null) {
            teacher.setPosition(modelMapper.map(dto.getPosition(), Position.class));
        }
        if (dto.getDepartment() != null) {
            teacher.setDepartment(modelMapper.map(dto.getDepartment(), Department.class));
        }
        if (dto.getRole() != null) {
            teacher.setRole(modelMapper.map(dto.getRole(), Role.class));
        }
        return teacher;
    }

    public TeachersDTO convertToDTO(Teachers teacher) {
        TeachersDTO dto = modelMapper.map(teacher, TeachersDTO.class);
        dto.setPosition(PositionDTO.builder().id(teacher.getPosition().getId()).name(teacher.getPosition().getName()).build());
        dto.setDepartment(DepartmentDTO.builder()
                .id(teacher.getDepartment().getId())
                .name(teacher.getDepartment().getName())
                .build());
        dto.setUniversity(UniversityDTO.builder()
                .id(teacher.getDepartment().getUniversity().getId())
                .name(teacher.getDepartment().getUniversity().getName())
                .build());
        dto.setRole(RoleDTO.builder()
                .id(teacher.getRole().getId())
                .name(teacher.getRole().getName())
                .build());
        return dto;
    }

    private ServiceInfoDTO prepareServiceInfo() {
        return ServiceInfoDTO.builder()
                .position(getPositions())
                .university(getUniversities())
                .build();

    }

    private List<UniversityDTO> getUniversities() {
        return serviceInfoService.getAllUniversities().stream()
                .map(university -> UniversityDTO.builder()
                        .id(university.getId())
                        .name(university.getName())
                        .abbr(university.getAbbr())
                        .department(serviceInfoService.getAllDepartmentsByUniversityId(university.getId()).stream()
                                .map(department -> DepartmentDTO.builder()
                                        .id(department.getId())
                                        .name(department.getName())
                                        .build())
                                .toList())
                        .build())
                .toList();
    }

    private List<PositionDTO> getPositions() {
        return serviceInfoService.getAllPositions().stream()
                .map(position -> PositionDTO.builder()
                        .id(position.getId())
                        .name(position.getName())
                        .build())
                .toList();
    }
}



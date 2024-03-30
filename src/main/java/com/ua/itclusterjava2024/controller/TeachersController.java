package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.*;
import com.ua.itclusterjava2024.entity.Degree;
import com.ua.itclusterjava2024.entity.Department;
import com.ua.itclusterjava2024.entity.Position;
import com.ua.itclusterjava2024.entity.University;
import com.ua.itclusterjava2024.exception.NotFoundException;
import com.ua.itclusterjava2024.service.implementation.*;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import com.ua.itclusterjava2024.entity.Teachers;
import com.ua.itclusterjava2024.service.implementation.TeachersServiceImpl;
import com.ua.itclusterjava2024.validators.TeachersValidator;
import com.ua.itclusterjava2024.wrappers.Patcher;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@RestController
@RequestMapping("/teachers")
public class TeachersController {

    private final TeachersServiceImpl teachersService;
    private final DegreeServiceImpl degreeService;
    private final PositionServiceImpl positionService;
    private final DepartmentServiceImpl departmentService;
    private final UniversityServiceImpl universityService;
    private final ModelMapper modelMapper;
    @Autowired
    Patcher patcher;
    private final TeachersValidator teachersValidator;

    @Autowired
    public TeachersController(TeachersServiceImpl teachersService, DegreeServiceImpl degreeService, PositionServiceImpl positionService, DepartmentServiceImpl departmentService, UniversityServiceImpl universityService, ModelMapper modelMapper, TeachersValidator teachersValidator) {
        this.teachersService = teachersService;
        this.degreeService = degreeService;
        this.positionService = positionService;
        this.departmentService = departmentService;
        this.universityService = universityService;
        this.modelMapper = modelMapper;
        this.teachersValidator = teachersValidator;
    }

    @GetMapping
    public PageWrapper<TeachersDTO> findAll(@RequestParam(defaultValue = "0") int page) {
        int pageSize = 20;
        PageRequest pageable = PageRequest.of(page, pageSize);
        Page<TeachersDTO> teachersPage = teachersService.getAll(pageable).map(this::convertToDTO);

        PageWrapper<TeachersDTO> pageWrapper = new PageWrapper<>();
        pageWrapper.setContent(teachersPage.getContent());
        pageWrapper.setPageNumber(teachersPage.getNumber());
        pageWrapper.setTotalElements(teachersPage.getTotalElements());
        return pageWrapper;
    }

    @PatchMapping("/{id}")
    public RedirectView updateEntity(@PathVariable("id") Long id, @RequestBody Teachers teachers) {
        Teachers existingTeacher = teachersService.readById(id).orElse(null);
        try {
            patcher.patch(existingTeacher, teachers);
            teachersService.create(existingTeacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RedirectView("/teachers");
    }


    @DeleteMapping("/{id}")
    public RedirectView delete(@PathVariable long id) {
        teachersService.delete(id);
        return new RedirectView("/teachers");
    }

    @GetMapping("/{id}")
    public TeachersDTO findById(@PathVariable long id) {
        return convertToDTO(teachersService.readById(id).orElse(null));
    }

    //TODO @RequestBody Teachers or TeachersDTO
    @PostMapping
    public RedirectView save(@RequestBody Teachers teachers, BindingResult bindingResult) {
        teachersService.create(teachers);
        return new RedirectView("/teachers");
    }

    public Teachers convertToEntity(TeachersDTO dto) {
        Teachers teacher = modelMapper.map(dto, Teachers.class);
        Position position = positionService.readById(dto.getPosition().getId())
                .orElseThrow(() -> new NotFoundException("Position not found"));
        Degree degree = degreeService.readById(dto.getDegree().getId())
                .orElseThrow(() -> new NotFoundException("Degree not found"));
        Department department = departmentService.readById(dto.getDepartment().getId())
                .orElseThrow(() -> new NotFoundException("Department not found"));
        University university = universityService.readById(dto.getUniversity().getId())
                .orElseThrow(() -> new NotFoundException("University not found"));

        teacher.setPosition_id(position);
        teacher.setDegree_id(degree);
        department.setUniversity_id(university);
        teacher.setDepartment_id(department);
        return teacher;
    }


    public TeachersDTO convertToDTO(Teachers teacher) {
        TeachersDTO dto = modelMapper.map(teacher, TeachersDTO.class);
        dto.setPosition(new PositionDTO(teacher.getPosition_id().getId(), teacher.getPosition_id().getName()));
        dto.setDegree(new DegreeDTO(teacher.getDegree_id().getId(), teacher.getDegree_id().getName()));
        dto.setUniversity(UniversityDTO.builder().id(teacher.getDepartment_id().getUniversity_id().getId()).name(teacher.getDepartment_id().getUniversity_id().getName()).build());
        dto.setDepartment(new DepartmentDTO(teacher.getDepartment_id().getId(), teacher.getDepartment_id().getName()));
        return dto;
    }
}



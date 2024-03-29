package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.*;
import com.ua.itclusterjava2024.entity.Degree;
import com.ua.itclusterjava2024.entity.Department;
import com.ua.itclusterjava2024.entity.Position;
import com.ua.itclusterjava2024.entity.University;
import com.ua.itclusterjava2024.service.implementation.*;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import com.ua.itclusterjava2024.entity.Teachers;
import com.ua.itclusterjava2024.exceptions.ValidationException;
import com.ua.itclusterjava2024.service.implementation.TeachersServiceImpl;
import com.ua.itclusterjava2024.validators.TeachersValidator;
import jakarta.validation.Valid;
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
    public RedirectView updateEntity(@PathVariable("id") Long id,
                                     @RequestBody @Valid TeachersDTO teachersDTO,
                                         BindingResult bindingResult) {
        Teachers existingTeacher = teachersService.readById(id);
        teachersValidator.validate(teachersDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        if (teachersDTO.getName() != null) {
            existingTeacher.setName(teachersDTO.getName());
        }

        if (teachersDTO.getPosition() != null) {
            Position position = positionService.readById(teachersDTO.getPosition().getId());
            existingTeacher.setPosition(position);
        }

        if (teachersDTO.getDegree() != null) {
            Degree degree = degreeService.readById(teachersDTO.getDegree().getId());
            existingTeacher.setDegree(degree);
        }

        if (teachersDTO.getDepartment() != null) {
            Department department = departmentService.readById(teachersDTO.getDepartment().getId());
            existingTeacher.setDepartment(department);
        }

        if (teachersDTO.getUniversity() != null) {
            University university = universityService.readById(teachersDTO.getUniversity().getId());
            Department department = existingTeacher.getDepartment();
            department.setUniversity(university);
        }

        if (teachersDTO.getEmail() != null) {
            existingTeacher.setEmail(teachersDTO.getEmail());
        }
        if (teachersDTO.getComments() != null) {
            existingTeacher.setComments(teachersDTO.getComments());
        }
        teachersService.create(existingTeacher);
        return new RedirectView("http://localhost:8080/teachers", true);
    }


    @DeleteMapping("/{id}")
    public RedirectView delete(@PathVariable long id) {
        teachersService.delete(id);
        return new RedirectView("/teachers");
    }

    @GetMapping("/{id}")
    public TeachersDTO findById(@PathVariable long id) {
        return convertToDTO(teachersService.readById(id));
    }

    //TODO @RequestBody Teachers or TeachersDTO
    @PostMapping
    public RedirectView save(@RequestBody @Valid TeachersDTO teachersDTO, BindingResult bindingResult) {
        teachersValidator.validate(teachersDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        service.create(convertToEntity(teachersDTO));
        return new RedirectView("/teachers");
    }

    //TODO змінити на Optional(?)
    public Teachers convertToEntity(TeachersDTO dto) {
        Teachers teacher = modelMapper.map(dto, Teachers.class);
        Position position = positionService.readById(dto.getPosition().getId());
        if (position == null) {
            throw new RuntimeException("Position not found");
        }
        Degree degree = degreeService.readById(dto.getDegree().getId());
        if (degree == null) {
            throw new RuntimeException("Degree not found");
        }
        Department department = departmentService.readById(dto.getDepartment().getId());
        if (department == null) {
            throw new RuntimeException("Department not found");
        }
        University university = universityService.readById(dto.getUniversity().getId());
        if (university == null) {
            throw new RuntimeException("University not found");
        }

        teacher.setPosition(position);
        teacher.setDegree(degree);
        department.setUniversity(university);
        teacher.setDepartment(department);
        return teacher;
    }


    public TeachersDTO convertToDTO(Teachers teacher) {
        TeachersDTO dto = modelMapper.map(teacher, TeachersDTO.class);
        dto.setPosition(new PositionDTO(teacher.getPosition().getId(), teacher.getPosition().getName()));
        dto.setDegree(new DegreeDTO(teacher.getDegree().getId(), teacher.getDegree().getName()));
        dto.setUniversity(new UniversityDTO(teacher.getDepartment().getUniversity().getId(), teacher.getDepartment().getUniversity().getName()));
        dto.setDepartment(new DepartmentDTO(teacher.getDepartment().getId(), teacher.getDepartment().getName()));
        return dto;
    }
}



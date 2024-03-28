package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.*;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import com.ua.itclusterjava2024.entity.Teachers;
import com.ua.itclusterjava2024.service.implementation.TeachersServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@RestController
@RequestMapping("/teachers")
public class TeachersController {

    private final TeachersServiceImpl service;
    private final ModelMapper modelMapper;

    @Autowired
    public TeachersController(TeachersServiceImpl service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public PageWrapper<TeachersDTO> findAll(@RequestParam(defaultValue = "0") int page) {
        int pageSize = 20;
        PageRequest pageable = PageRequest.of(page, pageSize);
        Page<TeachersDTO> teachersPage = service.getAll(pageable).map(this::convertToDTO);

        PageWrapper<TeachersDTO> pageWrapper = new PageWrapper<>();
        pageWrapper.setContent(teachersPage.getContent());
        pageWrapper.setPageNumber(teachersPage.getNumber());
        pageWrapper.setTotalElements(teachersPage.getTotalElements());
        return pageWrapper;
    }

    @PatchMapping("/{id}")
    public RedirectView updateEntity(@PathVariable("id") Long id,
                                     @RequestBody TeachersDTO teachersDTO) {
        service.update(id, convertToEntity(teachersDTO));
        return new RedirectView("/teachers");
    }

    @DeleteMapping("/{id}")
    public RedirectView delete(@PathVariable long id) {
        service.delete(id);
        return new RedirectView("/teachers");
    }

    @GetMapping("/{id}")
    public TeachersDTO findById(@PathVariable long id) {
        return convertToDTO(service.readById(id));
    }

    @PostMapping
    public RedirectView save(@RequestBody TeachersDTO teachersDTO) {
        service.create(convertToEntity(teachersDTO));
        return new RedirectView("/teachers");
    }

    private Teachers convertToEntity(TeachersDTO teachersDTO) {
        return modelMapper.map(teachersDTO, Teachers.class);
    }

    public TeachersDTO convertToDTO(Teachers teacher) {
        TeachersDTO dto = modelMapper.map(teacher, TeachersDTO.class);
        dto.setPosition(new PositionDTO(teacher.getPosition().getId(), teacher.getPosition().getName()));
        dto.setDegree(new DegreeDTO(teacher.getDegree().getId(), teacher.getDegree().getName()));
        dto.setUniversity(new UniversityDTO(teacher.getDepartment().getUniversity().getId(), teacher.getDepartment().getUniversity().getUniversity()));
        dto.setDepartment(new DepartmentDTO(teacher.getDepartment().getId(), teacher.getDepartment().getName()));
        return dto;
    }
}



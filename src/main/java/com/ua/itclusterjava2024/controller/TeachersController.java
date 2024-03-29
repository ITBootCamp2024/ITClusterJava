package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.controller.wrappers.PageWrapper;
import com.ua.itclusterjava2024.dto.TeachersDTO;
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

    private final TeachersServiceImpl service;
    private final ModelMapper modelMapper;
    private final TeachersValidator teachersValidator;

    @Autowired
    public TeachersController(TeachersServiceImpl service, ModelMapper modelMapper, TeachersValidator teachersValidator) {
        this.service = service;
        this.modelMapper = modelMapper;
        this.teachersValidator = teachersValidator;
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

    @PutMapping("/{id}")
    public RedirectView updateEntity(@PathVariable("id") Long id,
                                     @RequestBody @Valid TeachersDTO teachersDTO,
                                     BindingResult bindingResult) {
        teachersValidator.validate(teachersDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
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
    public RedirectView save(@RequestBody @Valid TeachersDTO teachersDTO, BindingResult bindingResult) {
        teachersValidator.validate(teachersDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        service.create(convertToEntity(teachersDTO));
        return new RedirectView("/teachers");
    }

    private Teachers convertToEntity(TeachersDTO teachersDTO) {
        return modelMapper.map(teachersDTO, Teachers.class);
    }

    private TeachersDTO convertToDTO(Teachers teachers) {
        return modelMapper.map(teachers, TeachersDTO.class);
    }
}



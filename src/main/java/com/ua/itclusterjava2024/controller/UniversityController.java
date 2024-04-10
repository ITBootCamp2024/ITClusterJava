package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.UniversityDTO;
import com.ua.itclusterjava2024.entity.University;

import com.ua.itclusterjava2024.exceptions.NotFoundException;
import com.ua.itclusterjava2024.exceptions.ValidationException;
import com.ua.itclusterjava2024.service.interfaces.UniversityService;
import com.ua.itclusterjava2024.validators.UniversityValidator;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import com.ua.itclusterjava2024.wrappers.Patcher;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/universities")
public class UniversityController {

    private final UniversityService universityService;
    private final ModelMapper modelMapper;
    private final UniversityValidator universityValidator;
    private final Patcher patcher;

    @Autowired
    public UniversityController(UniversityService universityService, ModelMapper modelMapper, UniversityValidator universityValidator, Patcher patcher) {
        this.universityService = universityService;
        this.modelMapper = modelMapper;
        this.universityValidator = universityValidator;
        this.patcher = patcher;
    }

    @GetMapping
    public PageWrapper<UniversityDTO> findAll() {
        List<UniversityDTO> universityPage = universityService.getAll().stream().map(this::convertToDTO).toList();

        PageWrapper<UniversityDTO> pageWrapper = new PageWrapper<>();
        pageWrapper.setContent(universityPage);
        pageWrapper.setTotalElements(universityPage.size());
        return pageWrapper;
    }

    @GetMapping("/{id}")
    public UniversityDTO findById(@PathVariable long id) {
        return convertToDTO(universityService.readById(id).orElse(null));
    }

    @CrossOrigin
    @PostMapping
    public PageWrapper<UniversityDTO> save(@RequestBody @Valid UniversityDTO universityDTO, BindingResult bindingResult) {
        universityValidator.validate(universityDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        universityService.create(convertToEntity(universityDTO));
        return findAll();
    }

    @CrossOrigin
    @PatchMapping("/{id}")
    public PageWrapper<UniversityDTO> update(@PathVariable Long id, @RequestBody University updatedUniversity) {
        University existingUniversity = universityService.readById(id)
                .orElseThrow(() -> new NotFoundException("University not found with id: " + id));
        try {
            patcher.patch(existingUniversity, updatedUniversity);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        universityService.update(id, existingUniversity);
        return findAll();
    }

    @DeleteMapping("/{id}")
    public PageWrapper<UniversityDTO> delete(@PathVariable long id) {
        universityService.delete(id);
        return findAll();
    }

    private University convertToEntity(UniversityDTO universityDTO) {
        return modelMapper.map(universityDTO, University.class);
    }

    private UniversityDTO convertToDTO(University university) {
        return modelMapper.map(university, UniversityDTO.class);
    }
}

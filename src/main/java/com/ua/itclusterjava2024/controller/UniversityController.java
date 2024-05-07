package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.UniversityDTO;
import com.ua.itclusterjava2024.entity.University;
import com.ua.itclusterjava2024.exceptions.ValidationException;
import com.ua.itclusterjava2024.service.interfaces.UniversityService;
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

@CrossOrigin
@RestController
@RequestMapping("/universities")
public class UniversityController {

    private final UniversityService universityService;
    private final ModelMapper modelMapper;
    private final Patcher<University> patcher;
    private final EntityManager entityManager;

    @Autowired
    public UniversityController(UniversityService universityService, ModelMapper modelMapper, Patcher<University> patcher, EntityManager entityManager) {
        this.universityService = universityService;
        this.modelMapper = modelMapper;
        this.patcher = patcher;
        this.entityManager = entityManager;
    }

    @GetMapping
    public PageWrapper<UniversityDTO> findAll() {
        List<UniversityDTO> universityPage = universityService.getAll()
                .stream().map(this::convertToDTO).toList();

        PageWrapper<UniversityDTO> pageWrapper = new PageWrapper<>();
        pageWrapper.setContent(universityPage);
        pageWrapper.setTotalElements(universityPage.size());
        return pageWrapper;
    }

    @GetMapping("/{id}")
    public UniversityDTO findById(@PathVariable long id) {
        return convertToDTO(universityService.readById(id).orElse(null));
    }


    @PostMapping
    public PageWrapper<UniversityDTO> save(@RequestBody @Valid UniversityDTO universityDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        universityService.create(convertToEntity(universityDTO));
        entityManager.clear();
        return findAll();
    }

    @PatchMapping("/{id}")
    public PageWrapper<UniversityDTO> update(@PathVariable Long id, @RequestBody University updatedUniversity) {
        University existingUniversity = universityService.readById(id)
                .orElseThrow(() -> new EntityNotFoundException("University not found with id: " + id));
        try {
            patcher.patch(existingUniversity, updatedUniversity);
            universityService.update(id, existingUniversity);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        entityManager.clear();
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

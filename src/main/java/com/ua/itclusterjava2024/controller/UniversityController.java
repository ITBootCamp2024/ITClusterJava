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
import org.springframework.web.servlet.view.RedirectView;

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
    public PageWrapper<UniversityDTO> findAll(@RequestParam(defaultValue = "1") int page) {
        int pageSize = 20;
        PageRequest pageable = PageRequest.of(page - 1, pageSize);
        Page<UniversityDTO> universityPage = universityService.getAll(pageable).map(this::convertToDTO);

        PageWrapper<UniversityDTO> pageWrapper = new PageWrapper<>();
        pageWrapper.setContent(universityPage.getContent());
        pageWrapper.setPageNumber(universityPage.getNumber());
        pageWrapper.setTotalElements(universityPage.getTotalElements());
        return pageWrapper;
    }

    @GetMapping("/{id}")
    public UniversityDTO findById(@PathVariable long id) {
        return convertToDTO(universityService.readById(id).orElse(null));
    }

    @CrossOrigin
    @PostMapping
    public RedirectView save(@RequestBody @Valid UniversityDTO universityDTO, BindingResult bindingResult) {
        universityValidator.validate(universityDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        universityService.create(convertToEntity(universityDTO));
        return new RedirectView("/universities");
    }

    //TODO приямається сутність, а не DTO
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

        universityService.update(id,existingUniversity);
        return findAll(1);
//        return new RedirectView("/universities");
    }

    @DeleteMapping("/{id}")
    public RedirectView delete(@PathVariable long id) {
        universityService.delete(id);
        return new RedirectView("/universities");
    }

    private University convertToEntity(UniversityDTO universityDTO) {
        return modelMapper.map(universityDTO, University.class);
    }

    private UniversityDTO convertToDTO(University university) {
        return modelMapper.map(university, UniversityDTO.class);
    }
}

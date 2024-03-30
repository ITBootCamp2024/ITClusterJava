package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.SchoolDTO;
import com.ua.itclusterjava2024.entity.School;
import com.ua.itclusterjava2024.exceptions.ValidationException;
import com.ua.itclusterjava2024.service.interfaces.SchoolService;
import com.ua.itclusterjava2024.validators.SchoolValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/school")
public class SchoolController {
    private final SchoolService schoolService;
    private final ModelMapper modelMapper;
    private final SchoolValidator schoolValidator;

    @Autowired
    public SchoolController(SchoolService schoolService, ModelMapper modelMapper, SchoolValidator schoolValidator) {
        this.schoolService = schoolService;
        this.modelMapper = modelMapper;
        this.schoolValidator = schoolValidator;
    }

    @GetMapping
    public List<SchoolDTO> findAll() {
        return schoolService.getAll().stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SchoolDTO findById(@PathVariable long id) {
        return convertToDTO(schoolService.readById(id));
    }

    @PostMapping
    public RedirectView save(@RequestBody @Valid SchoolDTO schoolDTO, BindingResult bindingResult) {
        schoolValidator.validate(schoolDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        schoolService.create(convertToEntity(schoolDTO));
        return new RedirectView("/school");
    }

    @PatchMapping("/{id}")
    public RedirectView update(@PathVariable("id") Long id,
                               @RequestBody @Valid SchoolDTO schoolDTO,
                               BindingResult bindingResult) {
        schoolValidator.validate(schoolDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        schoolService.update(id, convertToEntity(schoolDTO));
        return new RedirectView("/school");
    }

    @DeleteMapping("/{id}")
    public RedirectView delete(@PathVariable long id) {
        schoolService.delete(id);
        return new RedirectView("/school");
    }

    private School convertToEntity(SchoolDTO schoolDTO){
        return modelMapper.map(schoolDTO, School.class);
    }

    private SchoolDTO convertToDTO(School school){
        return modelMapper.map(school, SchoolDTO.class);
    }
}

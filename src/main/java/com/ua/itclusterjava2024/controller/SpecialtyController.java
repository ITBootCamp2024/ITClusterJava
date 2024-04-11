package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.SpecialtyDTO;
import com.ua.itclusterjava2024.dto.TeachersDTO;
import com.ua.itclusterjava2024.entity.Specialty;
import com.ua.itclusterjava2024.exceptions.NotFoundException;
import com.ua.itclusterjava2024.exceptions.ValidationException;
import com.ua.itclusterjava2024.service.interfaces.SpecialtyService;
import com.ua.itclusterjava2024.validators.SpecialtyValidator;
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

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/specialties")
public class SpecialtyController {
    private final SpecialtyService specialtyService;
    private final ModelMapper modelMapper;
    private final Patcher<Specialty> patcher;

    @Autowired
    public SpecialtyController(SpecialtyService specialtyService, ModelMapper modelMapper, Patcher<Specialty> patcher) {
        this.specialtyService = specialtyService;
        this.patcher = patcher;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public PageWrapper<SpecialtyDTO> findAll() {
        List<SpecialtyDTO> specialtyPage =  specialtyService.getAll().stream()
                .map(this::convertToDTO)
                .toList();
        PageWrapper<SpecialtyDTO> pageWrapper = new PageWrapper<>();
        pageWrapper.setContent(specialtyPage);
        pageWrapper.setTotalElements(specialtyPage.size());
        return pageWrapper;
    }

    @GetMapping("/{id}")
    public SpecialtyDTO findById(@PathVariable long id) {
        return convertToDTO(specialtyService.readById(id).orElse(null));
    }

    @PostMapping
    public PageWrapper<SpecialtyDTO> save(@RequestBody @Valid SpecialtyDTO specialtyDTO) {
        specialtyService.create(convertToEntity(specialtyDTO));
        return findAll();
    }

    @PatchMapping("/{id}")
    public PageWrapper<SpecialtyDTO> update(@PathVariable("id") Long id,
            @RequestBody SpecialtyDTO updatedSpecialtyDTO) {
        Specialty existingSpecialty = specialtyService.readById(id)
                .orElseThrow(() -> new NotFoundException("Speciality not found with id: " + id));
        Specialty updatedSpecialty = convertToEntity(updatedSpecialtyDTO);
        try{
            patcher.patch(existingSpecialty, updatedSpecialty);
            specialtyService.update(id, existingSpecialty);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return findAll();
    }

    @DeleteMapping("/{id}")
    public PageWrapper<SpecialtyDTO> delete(@PathVariable long id) {
        specialtyService.delete(id);
        return findAll();
    }

    private Specialty convertToEntity(SpecialtyDTO specialtyDTO){
        return modelMapper.map(specialtyDTO, Specialty.class);
    }

    private SpecialtyDTO convertToDTO(Specialty specialty){
        return modelMapper.map(specialty, SpecialtyDTO.class);
    }
}

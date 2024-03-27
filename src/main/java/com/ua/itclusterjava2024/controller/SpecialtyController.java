package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.SpecialtyDTO;
import com.ua.itclusterjava2024.entity.Specialty;
import com.ua.itclusterjava2024.service.interfaces.SpecialtyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/specialty")
public class SpecialtyController {

    private final SpecialtyService specialtyService;
    private final ModelMapper modelMapper;

    @Autowired
    public SpecialtyController(SpecialtyService specialtyService, ModelMapper modelMapper) {
        this.specialtyService = specialtyService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public Page<SpecialtyDTO> findAll(@RequestParam(defaultValue = "0") int page) {
        int pageSize = 20;
        PageRequest pageable = PageRequest.of(page, pageSize);
        return specialtyService.getAll(pageable).map(this::convertToDTO);
    }

    @GetMapping("/{id}")
    public SpecialtyDTO findById(@PathVariable long id) {
        return convertToDTO(specialtyService.readById(id));
    }

    @PostMapping
    public RedirectView save(@RequestBody SpecialtyDTO specialtyDTO) {
        specialtyService.create(convertToEntity(specialtyDTO));
        return new RedirectView("/specialty");
    }

    @PutMapping("/{id}")
    public RedirectView update(@PathVariable("id") Long id,
            @RequestBody SpecialtyDTO specialtyDTO
    ) {
         specialtyService.update(id, convertToEntity(specialtyDTO));
        return new RedirectView("/specialty");
    }

    @DeleteMapping("/{id}")
    public RedirectView delete(@PathVariable long id) {
        specialtyService.delete(id);
        return new RedirectView("/specialty");
    }

    private Specialty convertToEntity(SpecialtyDTO specialtyDTO){
        return modelMapper.map(specialtyDTO, Specialty.class);
    }

    private SpecialtyDTO convertToDTO(Specialty specialty){
        return modelMapper.map(specialty, SpecialtyDTO.class);
    }
}

package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.UniversityDTO;
import com.ua.itclusterjava2024.entity.University;
import com.ua.itclusterjava2024.service.interfaces.UniversityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/university")
public class UniversityController {

    private final UniversityService universityService;
    private final ModelMapper modelMapper;

    @Autowired
    public UniversityController(UniversityService universityService, ModelMapper modelMapper) {
        this.universityService = universityService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<UniversityDTO> findAll() {
        return universityService.getAll().stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UniversityDTO findById(@PathVariable long id) {
        return convertToDTO(universityService.readById(id));
    }

    @PostMapping
    public RedirectView save(@RequestBody UniversityDTO universityDTO) {
        universityService.create(convertToEntity(universityDTO));
        return new RedirectView("/university");
    }

    @PatchMapping("/{id}")
    public RedirectView update(@PathVariable("id") Long id,
            @RequestBody UniversityDTO universityDTO
    ) {
        universityService.update(id, convertToEntity(universityDTO));
        return new RedirectView("/university");
    }

    @DeleteMapping("/{id}")
    public RedirectView delete(@PathVariable long id) {
        universityService.delete(id);
        return new RedirectView("/university");
    }

    private University convertToEntity(UniversityDTO universityDTO){
        return modelMapper.map(universityDTO, University.class);
    }

    private UniversityDTO convertToDTO(University university){
        return modelMapper.map(university, UniversityDTO.class);
    }
}

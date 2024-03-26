package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.ProgramsDTO;
import com.ua.itclusterjava2024.dto.SpecialtyDTO;
import com.ua.itclusterjava2024.entity.Programs;
import com.ua.itclusterjava2024.entity.Specialty;
import com.ua.itclusterjava2024.service.interfaces.SpecialtyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

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
        return specialtyService.getAll(pageable).map(i -> convertToDTO(i));
    }

    @GetMapping("/{id}")
    public SpecialtyDTO findById(@PathVariable long id) {
        return convertToDTO(specialtyService.readById(id));
    }

    @PostMapping
    public ModelAndView save(@RequestBody SpecialtyDTO specialtyDTO) {
        specialtyService.create(convertToEntity(specialtyDTO));
        return new ModelAndView("redirect:/course_blocks");
    }

    @PutMapping("/{id}")
    public ModelAndView update(@PathVariable("id") Long id,
            @RequestBody SpecialtyDTO specialtyDTO
    ) {
         specialtyService.update(id, convertToEntity(specialtyDTO));
        return new ModelAndView("redirect:/course_blocks");
    }

    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable long id) {
        specialtyService.delete(id);
        return new ModelAndView("redirect:/course_blocks");
    }

    private Specialty convertToEntity(SpecialtyDTO specialtyDTO){
        return modelMapper.map(specialtyDTO, Specialty.class);
    }

    private SpecialtyDTO convertToDTO(Specialty specialty){
        return modelMapper.map(specialty, SpecialtyDTO.class);
    }
}

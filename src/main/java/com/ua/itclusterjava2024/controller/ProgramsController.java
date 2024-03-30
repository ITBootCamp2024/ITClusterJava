package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.ProgramsDTO;
import com.ua.itclusterjava2024.entity.Programs;
import com.ua.itclusterjava2024.service.interfaces.ProgramsService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/programs")
public class ProgramsController {
    private final ProgramsService programsService;

    private final ModelMapper modelMapper;

    @Autowired
    public ProgramsController(ProgramsService programsService, ModelMapper modelMapper) {
        this.programsService = programsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<ProgramsDTO> findAll() {
        return programsService.getAll().stream().map(i -> convertToDTO(i))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProgramsDTO findById(@PathVariable long id) {
        return convertToDTO(programsService.readById(id));
    }

    @PostMapping
    public ModelAndView save(@RequestBody ProgramsDTO programsDTO) {
        programsService.create(convertToEntity(programsDTO));
        return new ModelAndView("redirect:/course_blocks");
    }

    @PutMapping("/{id}")
    public ModelAndView update(@PathVariable("id") Long id,
            @RequestBody ProgramsDTO programsDTO
    ) {
        programsService.update(id, convertToEntity(programsDTO));
        return new ModelAndView("redirect:/course_blocks");
    }

    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable long id) {
        programsService.delete(id);
        return new ModelAndView("redirect:/course_blocks");
    }

    private Programs convertToEntity(ProgramsDTO programsDTO){
        return modelMapper.map(programsDTO, Programs.class);
    }

    private ProgramsDTO convertToDTO(Programs programs){
        return modelMapper.map(programs, ProgramsDTO.class);
    }
}

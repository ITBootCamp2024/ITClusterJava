package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.ProgramsDTO;
import com.ua.itclusterjava2024.dto.SchoolDTO;
import com.ua.itclusterjava2024.entity.Programs;
import com.ua.itclusterjava2024.entity.School;
import com.ua.itclusterjava2024.service.interfaces.SchoolService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/school")
public class SchoolController {
    private final SchoolService schoolService;
    private final ModelMapper modelMapper;

    @Autowired
    public SchoolController(SchoolService schoolService, ModelMapper modelMapper) {
        this.schoolService = schoolService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<SchoolDTO> findAll() {
        return schoolService.getAll().stream().map(i -> convertToDTO(i))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SchoolDTO findById(@PathVariable long id) {
        return convertToDTO(schoolService.readById(id));
    }

    @PostMapping
    public ModelAndView save(@RequestBody SchoolDTO schoolDTO) {
        schoolService.create(convertToEntity(schoolDTO));
        return new ModelAndView("redirect:/course_blocks");
    }

    @PutMapping("/{id}")
    public ModelAndView update(@PathVariable("id") Long id,
                               @RequestBody SchoolDTO schoolDTO) {
        schoolService.update(id, convertToEntity(schoolDTO));
        return new ModelAndView("redirect:/course_blocks");
    }

    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable long id) {
        schoolService.delete(id);
        return new ModelAndView("redirect:/course_blocks");
    }

    private School convertToEntity(SchoolDTO schoolDTO){
        return modelMapper.map(schoolDTO, School.class);
    }

    private SchoolDTO convertToDTO(School school){
        return modelMapper.map(school, SchoolDTO.class);
    }
}

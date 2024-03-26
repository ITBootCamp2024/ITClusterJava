package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.TeachersDTO;
import com.ua.itclusterjava2024.entity.Teachers;
import com.ua.itclusterjava2024.service.implementation.TeachersServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/teachers")
public class TeachersController {

    private final TeachersServiceImpl service;
    private final ModelMapper modelMapper;

    @Autowired
    public TeachersController(TeachersServiceImpl service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public Page<TeachersDTO> getAll(@RequestParam(defaultValue = "0") int page){
        int pageSize = 20;
        PageRequest pageable = PageRequest.of(page, pageSize);
        return service.getAll(pageable).map(i -> convertToDTO(i));
    }

    @PutMapping("/{id}")
    public ModelAndView updateEntity(@PathVariable("id") Long id,
                                       @RequestBody TeachersDTO teachersDTO) {
            service.update(id, convertToEntity(teachersDTO));
        return new ModelAndView("redirect:/course_blocks");
    }

    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable long id) {
        service.delete(id);
        return new ModelAndView("redirect:/course_blocks");
    }

    @GetMapping("/{id}")
    public TeachersDTO findById(@PathVariable long id) {
        return convertToDTO(service.readById(id));
    }

    @PostMapping
    public ModelAndView saveCourseBlock(@RequestBody TeachersDTO teachersDTO){
        service.create(convertToEntity(teachersDTO));
        return new ModelAndView("redirect:/course_blocks");
    }

    private Teachers convertToEntity(TeachersDTO teachersDTO){
        return modelMapper.map(teachersDTO, Teachers.class);
    }

    private TeachersDTO convertToDTO(Teachers teachers){
        return modelMapper.map(teachers, TeachersDTO.class);
    }
}



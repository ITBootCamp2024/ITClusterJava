package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.CourseDTO;
import com.ua.itclusterjava2024.dto.ProgramsDTO;
import com.ua.itclusterjava2024.entity.Course;
import com.ua.itclusterjava2024.entity.Programs;
import com.ua.itclusterjava2024.service.interfaces.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;
    private final ModelMapper modelMapper;

    @Autowired
    public CourseController(CourseService courseService, ModelMapper modelMapper) {
        this.courseService = courseService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<CourseDTO> findAll() {
        return courseService.getAll().stream().map(i -> convertToDTO(i))
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public CourseDTO findById(@PathVariable long id) {
        return convertToDTO(courseService.readById(id));
    }

    @PostMapping
    public ModelAndView save(@RequestBody CourseDTO courseDTO) {
        courseService.create(convertToEntity(courseDTO));
        return new ModelAndView("redirect:/course_blocks");
    }

    @PutMapping("/{id}")
    public ModelAndView update(@PathVariable("id") Long id,
            @RequestBody CourseDTO courseDTO
    ) {
        courseService.update(id, convertToEntity(courseDTO));
        return new ModelAndView("redirect:/course_blocks");
    }

    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable long id) {
        courseService.delete(id);
        return new ModelAndView("redirect:/course_blocks");
    }

    private Course convertToEntity(CourseDTO courseDTO){
        return modelMapper.map(courseDTO, Course.class);
    }

    private CourseDTO convertToDTO(Course course){
        return modelMapper.map(course, CourseDTO.class);
    }
}

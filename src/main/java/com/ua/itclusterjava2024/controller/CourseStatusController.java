package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.CourseStatusDTO;
import com.ua.itclusterjava2024.dto.ProgramsDTO;
import com.ua.itclusterjava2024.entity.CourseStatus;
import com.ua.itclusterjava2024.entity.Programs;
import com.ua.itclusterjava2024.service.interfaces.CourseStatusService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/course_statuses")
public class CourseStatusController {
    private final CourseStatusService courseStatusService;
    private final ModelMapper modelMapper;

    public CourseStatusController(CourseStatusService courseStatusService, ModelMapper modelMapper) {
        this.courseStatusService = courseStatusService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<CourseStatusDTO> showCourseStatusesList(){
        return courseStatusService.getAll().stream().map(i -> convertToDTO(i))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ModelAndView saveCourseStatus(@RequestBody CourseStatusDTO courseStatusDTO){
        courseStatusService.create(convertToEntity(courseStatusDTO));
        return new ModelAndView("redirect:/course_blocks");
    }

    @PutMapping("/{id}")
    public ModelAndView updateCourseStatus(@PathVariable("id") Long id,
                                         @RequestBody CourseStatusDTO courseStatusDTO){
        courseStatusService.update(id, convertToEntity(courseStatusDTO));
        return new ModelAndView("redirect:/course_blocks");
    }

    @GetMapping("/{id}")
    public CourseStatusDTO findById(@PathVariable Long id){
        return convertToDTO(courseStatusService.readById(id));
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteCourseStatus(@PathVariable Long id){
        courseStatusService.delete(id);
        return new ModelAndView("redirect:/course_blocks");
    }

    private CourseStatus convertToEntity(CourseStatusDTO courseStatusDTO){
        return modelMapper.map(courseStatusDTO, CourseStatus.class);
    }

    private CourseStatusDTO convertToDTO(CourseStatus courseStatus){
        return modelMapper.map(courseStatus, CourseStatusDTO.class);
    }
}

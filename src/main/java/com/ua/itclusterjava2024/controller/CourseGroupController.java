package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.CourseGroupDTO;
import com.ua.itclusterjava2024.dto.ProgramsDTO;
import com.ua.itclusterjava2024.entity.CourseGroup;
import com.ua.itclusterjava2024.entity.Programs;
import com.ua.itclusterjava2024.service.interfaces.CourseGroupService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/course_groupes")
public class CourseGroupController {
    private final CourseGroupService courseGroupService;
    private final ModelMapper modelMapper;

    public CourseGroupController(CourseGroupService courseGroupService, ModelMapper modelMapper) {
        this.courseGroupService = courseGroupService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<CourseGroupDTO> showCourseGroupesList(){
        return courseGroupService.getAll().stream().map(i -> convertToDTO(i))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ModelAndView saveCourseGroup(@RequestBody CourseGroupDTO courseGroupDTO){
        courseGroupService.create(convertToEntity(courseGroupDTO));
        return new ModelAndView("redirect:/course_blocks");
    }

    @PutMapping("/{id}")
    public ModelAndView updateCourseGroup(@PathVariable("id") Long id,
                                           @RequestBody CourseGroupDTO courseGroupDTO){
        courseGroupService.update(id, convertToEntity(courseGroupDTO));
        return new ModelAndView("redirect:/course_blocks");
    }

    @GetMapping("/{id}")
    public CourseGroupDTO findById(@PathVariable Long id){
        return convertToDTO(courseGroupService.readById(id));
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteCourseGroup(@PathVariable Long id){
        courseGroupService.delete(id);
        return new ModelAndView("redirect:/course_blocks");
    }

    private CourseGroup convertToEntity(CourseGroupDTO courseGroupDTO){
        return modelMapper.map(courseGroupDTO, CourseGroup.class);
    }

    private CourseGroupDTO convertToDTO(CourseGroup courseGroup){
        return modelMapper.map(courseGroup, CourseGroupDTO.class);
    }
}

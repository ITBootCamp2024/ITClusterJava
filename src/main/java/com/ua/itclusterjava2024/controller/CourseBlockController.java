package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.CourseBlockDTO;
import com.ua.itclusterjava2024.dto.ProgramsDTO;
import com.ua.itclusterjava2024.entity.CourseBlock;
import com.ua.itclusterjava2024.entity.Programs;
import com.ua.itclusterjava2024.service.interfaces.CourseBlockService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/course_blocks")
public class CourseBlockController {
    private final CourseBlockService courseBlockService;
    private final ModelMapper modelMapper;

    public CourseBlockController(CourseBlockService courseBlockService, ModelMapper modelMapper) {
        this.courseBlockService = courseBlockService;
        this.modelMapper = modelMapper;
    }
    @GetMapping
    public List<CourseBlockDTO> showCourseBlockList(){
        return courseBlockService.getAll().stream().map(i -> convertToDTO(i))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ModelAndView saveCourseBlock(@RequestBody CourseBlockDTO courseBlockDTO){
        courseBlockService.create(convertToEntity(courseBlockDTO));
        return new ModelAndView("redirect:/course_blocks");
    }

    @PutMapping("/{id}")
    public ModelAndView updateCourseBlock(@PathVariable("id") Long id,
                                         @RequestBody CourseBlockDTO courseBlockDTO){
        courseBlockService.update(id, convertToEntity(courseBlockDTO));
        return new ModelAndView("redirect:/course_blocks");
    }

    @GetMapping("/{id}")
    public CourseBlockDTO findById(@PathVariable Long id){
        return convertToDTO(courseBlockService.readById(id));
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteCourseBlock(@PathVariable Long id){
        courseBlockService.delete(id);
        return new ModelAndView("redirect:/course_blocks");
    }

    private CourseBlock convertToEntity(CourseBlockDTO courseBlockDTO){
        return modelMapper.map(courseBlockDTO, CourseBlock.class);
    }

    private CourseBlockDTO convertToDTO(CourseBlock courseBlock){
        return modelMapper.map(courseBlock, CourseBlockDTO.class);
    }
}

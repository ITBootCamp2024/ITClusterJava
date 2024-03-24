package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.entity.CourseGroup;
import com.ua.itclusterjava2024.service.interfaces.CourseGroupService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/course_groupes")
public class CourseGroupController {
    private final CourseGroupService courseGroupService;

    public CourseGroupController(CourseGroupService courseGroupService) {
        this.courseGroupService = courseGroupService;
    }

    @GetMapping
    public List<CourseGroup> showCourseGroupesList(){
        return courseGroupService.getAll();
    }

    @PostMapping
    public ModelAndView saveCourseGroup(@RequestBody CourseGroup courseGroup){
        courseGroupService.create(courseGroup);
        return new ModelAndView("redirect:/course_blocks");
    }

    @PutMapping("/{id}")
    public ModelAndView updateCourseGroup(@PathVariable("id") Long id,
                                           @RequestBody CourseGroup courseGroup){
        courseGroupService.update(id, courseGroup);
        return new ModelAndView("redirect:/course_blocks");
    }

    @GetMapping("/{id}")
    public CourseGroup findById(@PathVariable Long id){
        return courseGroupService.readById(id);
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteCourseGroup(@PathVariable Long id){
        courseGroupService.delete(id);
        return new ModelAndView("redirect:/course_blocks");
    }
}

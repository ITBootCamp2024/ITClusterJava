package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.entity.Course;
import com.ua.itclusterjava2024.service.interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> findAll() {
        return courseService.getAll();
    }
    @GetMapping("/{id}")
    public Course findById(@PathVariable long id) {
        return courseService.readById(id);
    }

    @PostMapping
    public ModelAndView save(@RequestBody Course course) {
        courseService.create(course);
        return new ModelAndView("redirect:/course_blocks");
    }

    @PutMapping("/{id}")
    public ModelAndView update(@PathVariable("id") Long id,
            @RequestBody Course course
    ) {
        courseService.update(id, course);
        return new ModelAndView("redirect:/course_blocks");
    }

    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable long id) {
        courseService.delete(id);
        return new ModelAndView("redirect:/course_blocks");
    }
}

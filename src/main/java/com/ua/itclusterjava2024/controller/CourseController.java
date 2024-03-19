package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.entity.Course;
import com.ua.itclusterjava2024.entity.University;
import com.ua.itclusterjava2024.service.interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Course save(@RequestBody Course course) {
        return courseService.create(course);
    }

    @PutMapping("/{id}")
    public Course update(
            @RequestBody Course course
    ) {
        return courseService.update(course);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        courseService.delete(id);
    }
}

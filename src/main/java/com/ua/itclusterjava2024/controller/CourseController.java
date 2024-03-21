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
    public List<Course> save(@RequestBody Course course) {
        courseService.create(course);
        return courseService.getAll();
    }

    @PutMapping("/{id}")
    public List<Course> update(
            @RequestBody Course course
    ) {
        courseService.update(course);
        return courseService.getAll();
    }

    @DeleteMapping("/{id}")
    public List<Course> delete(@PathVariable long id) {
        courseService.delete(id);
        return courseService.getAll();
    }
}

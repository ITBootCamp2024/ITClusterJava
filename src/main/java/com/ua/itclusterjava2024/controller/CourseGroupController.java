package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.entity.CourseGroup;
import com.ua.itclusterjava2024.service.interfaces.CourseGroupService;
import org.springframework.web.bind.annotation.*;

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
    public List<CourseGroup> saveCourseGroup(@RequestBody CourseGroup courseGroup){
        courseGroupService.create(courseGroup);
        return courseGroupService.getAll();
    }

    @PutMapping("/{id}")
    public List<CourseGroup> updateCourseGroup(@PathVariable("id") Long id,
                                           @RequestBody CourseGroup courseGroup){
        courseGroupService.update(id, courseGroup);
        return courseGroupService.getAll();
    }

    @GetMapping("/{id}")
    public CourseGroup findById(@PathVariable Long id){
        return courseGroupService.readById(id);
    }

    @DeleteMapping("/{id}")
    public List<CourseGroup> deleteCourseGroup(@PathVariable Long id){
        courseGroupService.delete(id);
        return courseGroupService.getAll();
    }
}

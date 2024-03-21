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
    public CourseGroup saveCourseGroup(@RequestBody CourseGroup courseGroup){
        return courseGroupService.create(courseGroup);
    }

    @PutMapping("/{id}")
    public CourseGroup updateCourseGroup(//@PathVariable Long id,
                                           @RequestBody CourseGroup courseGroup){
        return courseGroupService.update(courseGroup);
    }

    @GetMapping("/{id}")
    public CourseGroup findById(@PathVariable Long id){
        return courseGroupService.readById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteCourseGroup(@PathVariable Long id){
        courseGroupService.delete(id);
        return "Successfully deleted";
    }
}

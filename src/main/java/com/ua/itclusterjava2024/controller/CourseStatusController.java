package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.entity.CourseStatus;
import com.ua.itclusterjava2024.service.interfaces.CourseStatusService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course_statuses")
public class CourseStatusController {
    private final CourseStatusService courseStatusService;

    public CourseStatusController(CourseStatusService courseStatusService) {
        this.courseStatusService = courseStatusService;
    }

    @GetMapping
    public List<CourseStatus> showCourseStatusesList(){
        return courseStatusService.getAll();
    }

    @PostMapping
    public List<CourseStatus> saveCourseStatus(@RequestBody CourseStatus courseStatus){
        courseStatusService.create(courseStatus);
        return courseStatusService.getAll();
    }

    @PutMapping("/{id}")
    public List<CourseStatus> updateCourseStatus(//@PathVariable Long id,
                                         @RequestBody CourseStatus courseStatus){
        courseStatusService.update(courseStatus);
        return courseStatusService.getAll();
    }

    @GetMapping("/{id}")
    public CourseStatus findById(@PathVariable Long id){
        return courseStatusService.readById(id);
    }

    @DeleteMapping("/{id}")
    public List<CourseStatus> deleteCourseStatus(@PathVariable Long id){
        courseStatusService.delete(id);
        return courseStatusService.getAll();
    }
}

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

    // Need to complete
    @PostMapping("/save")
    public CourseStatus saveCourseStatus(@RequestBody CourseStatus courseStatus){
        return courseStatusService.create(courseStatus);
    }

    // Need to complete
    @PutMapping("/{id}")
    public CourseStatus updateCourseStatus(//@PathVariable Long id,
                                         @RequestBody CourseStatus courseStatus){
        return courseStatusService.update(courseStatus);
    }

    @GetMapping("/{id}")
    public CourseStatus findById(@PathVariable Long id){
        return courseStatusService.readById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourseStatus(@PathVariable Long id){
        courseStatusService.delete(id);
        return "Successfully deleted";
    }
}

package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.entity.CourseStatus;
import com.ua.itclusterjava2024.service.interfaces.CourseStatusService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView saveCourseStatus(@RequestBody CourseStatus courseStatus){
        courseStatusService.create(courseStatus);
        return new ModelAndView("redirect:/course_blocks");
    }

    @PutMapping("/{id}")
    public ModelAndView updateCourseStatus(@PathVariable("id") Long id,
                                         @RequestBody CourseStatus courseStatus){
        courseStatusService.update(id, courseStatus);
        return new ModelAndView("redirect:/course_blocks");
    }

    @GetMapping("/{id}")
    public CourseStatus findById(@PathVariable Long id){
        return courseStatusService.readById(id);
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteCourseStatus(@PathVariable Long id){
        courseStatusService.delete(id);
        return new ModelAndView("redirect:/course_blocks");
    }
}

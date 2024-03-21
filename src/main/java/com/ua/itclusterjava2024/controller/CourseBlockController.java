package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.entity.CourseBlock;
import com.ua.itclusterjava2024.service.implementation.CourseBlockServiceImpl;
import com.ua.itclusterjava2024.service.interfaces.CourseBlockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course_blocks")
public class CourseBlockController {
    private final CourseBlockService courseBlockService;

    public CourseBlockController(CourseBlockService courseBlockService) {
        this.courseBlockService = courseBlockService;
    }
    @GetMapping("/course_blocks")
    public List<CourseBlock> showCourseBlockList(){
        return courseBlockService.getAll();
    }

    // Need to complete
    @PostMapping("/course_blocks/save")
    public CourseBlock saveCourseBlock(@RequestBody CourseBlock courseBlock){
        return courseBlockService.create(courseBlock);
    }

    // Need to complete
    @PutMapping("/course_blocks/{id}")
    public CourseBlock updateCourseBlock(//@PathVariable Long id,
                                         @RequestBody CourseBlock courseBlock){
        return courseBlockService.update(courseBlock);
    }

    @GetMapping("/course_blocks/{id}")
    public CourseBlock findById(@PathVariable Long id){
        return courseBlockService.readById(id);
    }

    @DeleteMapping("/course_blocks/delete/{id}")
    public String deleteCourseBlock(@PathVariable Long id){
        courseBlockService.delete(id);
        return "Successfully deleted";
    }
}

package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.entity.CourseBlock;
import com.ua.itclusterjava2024.service.interfaces.CourseBlockService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course_blocks")
public class CourseBlockController {
    private final CourseBlockService courseBlockService;

    public CourseBlockController(CourseBlockService courseBlockService) {
        this.courseBlockService = courseBlockService;
    }
    @GetMapping
    public List<CourseBlock> showCourseBlockList(){
        return courseBlockService.getAll();
    }

    @PostMapping
    public CourseBlock saveCourseBlock(@RequestBody CourseBlock courseBlock){
        return courseBlockService.create(courseBlock);
    }

    @PutMapping("/{id}")
    public CourseBlock updateCourseBlock(//@PathVariable Long id,
                                         @RequestBody CourseBlock courseBlock){
        return courseBlockService.update(courseBlock);
    }

    @GetMapping("/{id}")
    public CourseBlock findById(@PathVariable Long id){
        return courseBlockService.readById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteCourseBlock(@PathVariable Long id){
        courseBlockService.delete(id);
        return "Successfully deleted";
    }
}

package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.entity.CourseBlock;
import com.ua.itclusterjava2024.service.CourseBlockServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class CourseBlockController {
    private final CourseBlockServiceImpl courseBlockService;

    public CourseBlockController(CourseBlockServiceImpl courseBlockService) {
        this.courseBlockService = courseBlockService;
    }

    @GetMapping("/course-block")
    public List<CourseBlock> showCourseBlockList(Model model){
        return courseBlockService.findAll();
    }

    // Need to complete
    @PostMapping("/course-block/save")
    public CourseBlock saveCourseBlock(@RequestBody CourseBlock courseBlock){
        return courseBlockService.saveCourseBlock(courseBlock);
    }

    // Need to complete
    @PostMapping("/course-block/{id}")
    public CourseBlock updateCourseBlock(@PathVariable Long id, @RequestBody CourseBlock courseBlock){
        return courseBlockService.updateCourseBlock(id, courseBlock);
    }

    @PostMapping("/course-block/delete/{id}")
    public String deleteCourseBlock(@PathVariable Long id){
        courseBlockService.deleteCourseBlockById(id);
        return "Successfully deleted";
    }
}

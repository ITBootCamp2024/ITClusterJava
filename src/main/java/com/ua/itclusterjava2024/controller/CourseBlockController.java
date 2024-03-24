package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.entity.CourseBlock;
import com.ua.itclusterjava2024.service.interfaces.CourseBlockService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.Pageable;
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
    public ModelAndView saveCourseBlock(@RequestBody CourseBlock courseBlock){
        courseBlockService.create(courseBlock);
        return new ModelAndView("redirect:/course_blocks");
    }

    @PutMapping("/{id}")
    public ModelAndView updateCourseBlock(@PathVariable("id") Long id,
                                         @RequestBody CourseBlock courseBlock){
        courseBlockService.update(id, courseBlock);
        return new ModelAndView("redirect:/course_blocks");
    }

    @GetMapping("/{id}")
    public CourseBlock findById(@PathVariable Long id){
        return courseBlockService.readById(id);
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteCourseBlock(@PathVariable Long id){
        courseBlockService.delete(id);
        return new ModelAndView("redirect:/course_blocks");
    }
}

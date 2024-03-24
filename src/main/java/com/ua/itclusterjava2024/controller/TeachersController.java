package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.entity.Teachers;
import com.ua.itclusterjava2024.service.implementation.TeachersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeachersController {

    private final TeachersServiceImpl service;

    @Autowired
    public TeachersController(TeachersServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public Page<Teachers> getAll(@RequestParam(defaultValue = "0") int page){
        int pageSize = 20;
        PageRequest pageable = PageRequest.of(page, pageSize);
        return service.getAll(pageable);
    }

    @PutMapping("/{id}")
    public ModelAndView updateEntity(@PathVariable("id") Long id,
                                       @RequestBody Teachers updatedTeachers) {
            service.update(id, updatedTeachers);
        return new ModelAndView("redirect:/course_blocks");
    }

    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable long id) {
        service.delete(id);
        return new ModelAndView("redirect:/course_blocks");
    }

    @GetMapping("/{id}")
    public Teachers findById(@PathVariable long id) {
        return service.readById(id);
    }

    @PostMapping
    public ModelAndView saveCourseBlock(@RequestBody Teachers newTeachers){
        service.create(newTeachers);
        return new ModelAndView("redirect:/course_blocks");
    }
}



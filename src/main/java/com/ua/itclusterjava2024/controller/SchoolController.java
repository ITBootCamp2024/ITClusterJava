package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.entity.Programs;
import com.ua.itclusterjava2024.entity.School;
import com.ua.itclusterjava2024.service.interfaces.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school")
public class SchoolController {
    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping
    public List<School> findAll() {
        return schoolService.getAll();
    }

    @GetMapping("/{id}")
    public School findById(@PathVariable long id) {
        return schoolService.readById(id);
    }

    @PostMapping
    public List<School> save(@RequestBody School school) {
        schoolService.create(school);
        return schoolService.getAll();
    }

    @PutMapping("/{id}")
    public List<School> update(@PathVariable("id") Long id,
                               @RequestBody School school) {
        schoolService.update(id, school);
        return schoolService.getAll();
    }

    @DeleteMapping("/{id}")
    public List<School> delete(@PathVariable long id) {
        schoolService.delete(id);
        return schoolService.getAll();
    }
}

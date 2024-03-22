package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.entity.University;
import com.ua.itclusterjava2024.service.interfaces.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/university")
public class UniversityController {

    private final UniversityService universityService;

    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping
    public List<University> findAll() {
        return universityService.getAll();
    }

    @GetMapping("/{id}")
    public University findById(@PathVariable long id) {
        return universityService.readById(id);
    }

    @PostMapping
    public List<University> save(@RequestBody University university) {
        universityService.create(university);
        return universityService.getAll();
    }

    @PutMapping("/{id}")
    public List<University> update(@PathVariable("id") Long id,
            @RequestBody University university
    ) {
        universityService.update(id, university);
        return universityService.getAll();
    }

    @DeleteMapping("/{id}")
    public List<University> delete(@PathVariable long id) {
        universityService.delete(id);
        return universityService.getAll();
    }
}

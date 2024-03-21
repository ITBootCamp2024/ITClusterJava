package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.entity.University;
import com.ua.itclusterjava2024.service.interfaces.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/university")
@CrossOrigin
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
    public University save(@RequestBody University university) {
        return universityService.create(university);
    }

    @PutMapping("/{id}")
    public University update(
            @RequestBody University university
    ) {
        return universityService.update(university);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        universityService.delete(id);
    }
}

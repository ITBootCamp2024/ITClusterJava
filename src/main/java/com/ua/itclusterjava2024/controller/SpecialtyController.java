package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.entity.Specialty;
import com.ua.itclusterjava2024.service.interfaces.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specialty")
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    @Autowired
    public SpecialtyController(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @GetMapping
    public List<Specialty> findAll() {
        return specialtyService.getAll();
    }

    @GetMapping("/{id}")
    public Specialty findById(@PathVariable long id) {
        return specialtyService.readById(id);
    }

    @PostMapping
    public Specialty save(@RequestBody Specialty specialty) {
        return specialtyService.create(specialty);
    }

    @PutMapping("/{id}")
    public Specialty update(
            @RequestBody Specialty specialty
    ) {
        return specialtyService.update(specialty);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        specialtyService.delete(id);
    }
}

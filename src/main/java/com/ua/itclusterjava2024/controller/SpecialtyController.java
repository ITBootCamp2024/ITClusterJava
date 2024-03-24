package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.entity.Specialty;
import com.ua.itclusterjava2024.service.interfaces.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public Page<Specialty> findAll(@RequestParam(defaultValue = "0") int page) {
        int pageSize = 20;
        PageRequest pageable = PageRequest.of(page, pageSize);
        return specialtyService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public Specialty findById(@PathVariable long id) {
        return specialtyService.readById(id);
    }

    @PostMapping
    public ModelAndView save(@RequestBody Specialty specialty) {
        specialtyService.create(specialty);
        return new ModelAndView("redirect:/course_blocks");
    }

    @PutMapping("/{id}")
    public ModelAndView update(@PathVariable("id") Long id,
            @RequestBody Specialty specialty
    ) {
         specialtyService.update(id, specialty);
        return new ModelAndView("redirect:/course_blocks");
    }

    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable long id) {
        specialtyService.delete(id);
        return new ModelAndView("redirect:/course_blocks");

    }
}

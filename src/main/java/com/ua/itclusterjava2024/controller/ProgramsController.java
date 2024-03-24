package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.entity.Programs;
import com.ua.itclusterjava2024.service.interfaces.ProgramsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/programs")
public class ProgramsController {
    private final ProgramsService programsService;

    @Autowired
    public ProgramsController(ProgramsService programsService) {
        this.programsService = programsService;
    }

    @GetMapping
    public List<Programs> findAll() {
        return programsService.getAll();
    }

    @GetMapping("/{id}")
    public Programs findById(@PathVariable long id) {
        return programsService.readById(id);
    }

    @PostMapping
    public ModelAndView save(@RequestBody Programs programs) {
        programsService.create(programs);
        return new ModelAndView("redirect:/course_blocks");
    }

    @PutMapping("/{id}")
    public ModelAndView update(@PathVariable("id") Long id,
            @RequestBody Programs programs
    ) {
        programsService.update(id, programs);
        return new ModelAndView("redirect:/course_blocks");
    }

    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable long id) {
        programsService.delete(id);
        return new ModelAndView("redirect:/course_blocks");
    }
}

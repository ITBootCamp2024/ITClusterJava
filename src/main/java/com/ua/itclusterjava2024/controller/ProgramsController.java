package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.entity.Programs;
import com.ua.itclusterjava2024.service.ProgramsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Programs save(@RequestBody Programs programs) {
        return programsService.create(programs);
    }

    @PutMapping("/{id}")
    public Programs update(
            @RequestBody Programs programs
    ) {
        return programsService.update(programs);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        programsService.delete(id);
    }
}

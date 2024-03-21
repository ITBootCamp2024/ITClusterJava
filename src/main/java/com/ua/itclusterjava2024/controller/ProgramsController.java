package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.entity.Programs;
import com.ua.itclusterjava2024.service.interfaces.ProgramsService;
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
    public List<Programs> save(@RequestBody Programs programs) {
        programsService.create(programs);
        return programsService.getAll();
    }

    @PutMapping("/{id}")
    public List<Programs> update(
            @RequestBody Programs programs
    ) {
        programsService.update(programs);
        return programsService.getAll();
    }

    @DeleteMapping("/{id}")
    public List<Programs> delete(@PathVariable long id) {
        programsService.delete(id);
        return programsService.getAll();
    }
}

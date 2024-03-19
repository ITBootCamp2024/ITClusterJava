package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.entity.Course;
import com.ua.itclusterjava2024.entity.ProgramsLevel;
import com.ua.itclusterjava2024.service.interfaces.ProgramsLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programs_levels")
public class ProgramsLevelController {

    private final ProgramsLevelService programsLevelService;

    @Autowired
    public ProgramsLevelController(ProgramsLevelService programsLevelService) {
        this.programsLevelService = programsLevelService;
    }

    @GetMapping
    public List<ProgramsLevel> findAll() {
        return programsLevelService.getAll();
    }

    @GetMapping("/{id}")
    public ProgramsLevel findById(@PathVariable long id) {
        return programsLevelService.readById(id);
    }

    @PostMapping
    public ProgramsLevel save(@RequestBody ProgramsLevel programsLevel) {
        return programsLevelService.create(programsLevel);
    }

    @PutMapping("/{id}")
    public ProgramsLevel update(
            @RequestBody ProgramsLevel programsLevel
    ) {
        return programsLevelService.update(programsLevel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        programsLevelService.delete(id);
    }
}

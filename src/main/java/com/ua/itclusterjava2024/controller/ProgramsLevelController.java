package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.entity.ProgramsLevel;
import com.ua.itclusterjava2024.service.interfaces.ProgramsLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView save(@RequestBody ProgramsLevel programsLevel) {
        programsLevelService.create(programsLevel);
        return new ModelAndView("redirect:/course_blocks");
    }

    @PutMapping("/{id}")
    public ModelAndView update(@PathVariable("id") Long id,
            @RequestBody ProgramsLevel programsLevel
    ) {
        programsLevelService.update(id, programsLevel);
        return new ModelAndView("redirect:/course_blocks");
    }

    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable long id) {
        programsLevelService.delete(id);
        return new ModelAndView("redirect:/course_blocks");
    }
}

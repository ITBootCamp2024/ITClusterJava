package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.ProgramsDTO;
import com.ua.itclusterjava2024.dto.ProgramsLevelDTO;
import com.ua.itclusterjava2024.entity.Programs;
import com.ua.itclusterjava2024.entity.ProgramsLevel;
import com.ua.itclusterjava2024.service.interfaces.ProgramsLevelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/programs_levels")
public class ProgramsLevelController {

    private final ProgramsLevelService programsLevelService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProgramsLevelController(ProgramsLevelService programsLevelService, ModelMapper modelMapper) {
        this.programsLevelService = programsLevelService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<ProgramsLevelDTO> findAll() {
        return programsLevelService.getAll().stream().map(i -> convertToDTO(i))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProgramsLevelDTO findById(@PathVariable long id) {
        return convertToDTO(programsLevelService.readById(id));
    }

    @PostMapping
    public ModelAndView save(@RequestBody ProgramsLevelDTO programsLevelDTO) {
        programsLevelService.create(convertToEntity(programsLevelDTO));
        return new ModelAndView("redirect:/course_blocks");
    }

    @PutMapping("/{id}")
    public ModelAndView update(@PathVariable("id") Long id,
            @RequestBody ProgramsLevelDTO programsLevelDTO
    ) {
        programsLevelService.update(id, convertToEntity(programsLevelDTO));
        return new ModelAndView("redirect:/course_blocks");
    }

    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable long id) {
        programsLevelService.delete(id);
        return new ModelAndView("redirect:/course_blocks");
    }

    private ProgramsLevel convertToEntity(ProgramsLevelDTO programsLevelDTO){
        return modelMapper.map(programsLevelDTO, ProgramsLevel.class);
    }

    private ProgramsLevelDTO convertToDTO(ProgramsLevel programsLevel){
        return modelMapper.map(programsLevel, ProgramsLevelDTO.class);
    }
}

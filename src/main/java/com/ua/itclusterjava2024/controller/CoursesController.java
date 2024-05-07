package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.*;
import com.ua.itclusterjava2024.service.interfaces.DisciplinesService;
import com.ua.itclusterjava2024.service.interfaces.SyllabusesService;
import com.ua.itclusterjava2024.service.interfaces.TeachersService;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/courses")
public class CoursesController {
    final TeachersService teachersService;
    final DisciplinesService disciplinesService;
    final SyllabusesService syllabusesService;

    public CoursesController(TeachersService teachersService, DisciplinesService disciplinesService, SyllabusesService syllabusesService) {
        this.teachersService = teachersService;
        this.disciplinesService = disciplinesService;
        this.syllabusesService = syllabusesService;
    }

    @GetMapping
    public PageWrapper<CoursesDTO> findAll(@RequestParam("teacher_id") Long teacherId) {
        List<TeachersDTO> teachers = teachersService.readById(teacherId).stream()
                .map(teachers1 -> TeachersDTO.builder().id(teachers1.getId()).name(teachers1.getName()).build()).toList();
        List<DisciplinesDTO> disciplinesDTOS = disciplinesService.findByTeacherId(teacherId).stream()
                .map(discipline -> DisciplinesDTO.builder().id(discipline.getId()).name(discipline.getName()).build())
                .toList();
        List<SyllabusesDTO> syllabusesDTOs = new ArrayList<>();
        for (DisciplinesDTO disciplineDTO : disciplinesDTOS) {
            List<SyllabusesDTO> syllabuses = syllabusesService.findByDisciplineId(disciplineDTO.getId()).stream()
                    .map(syllabus -> SyllabusesDTO.builder().id(syllabus.getId()).name(syllabus.getName()).status(syllabus.getStatus()).build())
                    .toList();
            syllabusesDTOs.addAll(syllabuses);
        }
        PageWrapper<CoursesDTO> pageWrapper = new PageWrapper<>();
        List<CoursesDTO> coursesDTOS = List.of(CoursesDTO.builder().teacherDTO(teachers).disciplinesDTO(disciplinesDTOS).syllabusesDTO(syllabusesDTOs).build());
        pageWrapper.setContent(coursesDTOS);
        pageWrapper.setTotalElements(teachers.size());
        return pageWrapper;
    }
}

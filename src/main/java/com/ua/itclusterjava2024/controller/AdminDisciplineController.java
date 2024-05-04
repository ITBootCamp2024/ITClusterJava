package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.*;
import com.ua.itclusterjava2024.entity.*;
import com.ua.itclusterjava2024.service.interfaces.DisciplineGroupService;
import com.ua.itclusterjava2024.service.interfaces.DisciplinesService;
import com.ua.itclusterjava2024.service.interfaces.SpecialistService;
import com.ua.itclusterjava2024.service.interfaces.SyllabusesService;
import com.ua.itclusterjava2024.wrappers.DisciplinePageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/disciplines/verified")
public class AdminDisciplineController {
    private final DisciplinesService disciplinesService;
    private final DisciplineGroupService disciplineGroupsService;
    private final SyllabusesService syllabusesService;
    private final SpecialistService specialistService;

    @Autowired
    public AdminDisciplineController(DisciplinesService disciplinesService, DisciplineGroupService disciplineGroupsService, SyllabusesService syllabusesService, SpecialistService specialistService) {
        this.disciplinesService = disciplinesService;
        this.disciplineGroupsService = disciplineGroupsService;
        this.syllabusesService = syllabusesService;
        this.specialistService = specialistService;
    }

    @GetMapping("/{admin_id}")
    public ResponseEntity<DisciplinePageWrapper> getVerifiedDiscipline(@PathVariable("admin_id") Long adminId) {
        List<SpecialistDTO> allSpecialistsDTO = specialistService.getAll().stream()
                .map(this::convertSpecialistToDTO)
                .toList();

        List<DisciplineGroupsDTO> allDisciplineGroupsDTO = disciplineGroupsService.getAll().stream()
                .map(this::convertDisciplineGroupsToDTO)
                .toList();

        DisciplinePageWrapper response = new DisciplinePageWrapper(
                new DisciplinePageWrapper.Content(adminId, allDisciplineGroupsDTO),
                allSpecialistsDTO
        );

        return ResponseEntity.ok(response);
    }

    private SpecialistDTO convertSpecialistToDTO(Specialist specialist) {
        return SpecialistDTO.builder()
                .id(specialist.getId())
                .name(specialist.getName())
                .professionalField(specialist.getProfessionalField())
                .build();
    }

    private DisciplineGroupsDTO convertDisciplineGroupsToDTO(DisciplineGroups disciplineGroups) {
        return DisciplineGroupsDTO.builder()
                .id(disciplineGroups.getId())
                .name(disciplineGroups.getName())
                .disciplines(disciplinesService.findByDisciplineGroupId(disciplineGroups.getId()).stream()
                        .map(this::convertDisciplineToDTO)
                        .toList())
                .build();
    }

    private DisciplinesDTO convertDisciplineToDTO(Disciplines discipline) {
        return DisciplinesDTO.builder()
                .id(discipline.getId())
                .name(discipline.getName())
                .syllabuses(convertSyllabusesToDTO(syllabusesService.findByDisciplineId(discipline.getId()).get(0)))
                .build();
    }

    private SyllabusesDTO convertSyllabusesToDTO(Syllabuses syllabus) {
        return SyllabusesDTO.builder()
                .id(syllabus.getId())
                .name(syllabus.getName())
                .status(syllabus.getStatus())
                .build();
    }

}

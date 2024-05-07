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
    private final SyllabusesService syllabusesService;
    private final SpecialistService specialistService;

    @Autowired
    public AdminDisciplineController(SyllabusesService syllabusesService, SpecialistService specialistService) {
        this.syllabusesService = syllabusesService;
        this.specialistService = specialistService;
    }

    @GetMapping
    public ResponseEntity<DisciplinePageWrapper> find() {
        List<DisciplinesDTO> disciplinesDTO = syllabusesService.getAll().stream()
                .map(this::convertSyllabusesToDisciplinesDTO)
                .toList();

        List<SpecialistDTO> specialistsDTO = specialistService.getAll().stream()
                .map(this::convertSpecialistToDTO)
                .toList();

        DisciplinePageWrapper response = new DisciplinePageWrapper(disciplinesDTO, specialistsDTO);

        return ResponseEntity.ok(response);
    }

    private SpecialistDTO convertSpecialistToDTO(Specialist specialist) {
        return SpecialistDTO.builder()
                .id(specialist.getId())
                .name(specialist.getName())
                .professionalField(specialist.getProfessionalField())
                .company(specialist.getCompany())
                .build();
    }

    private DisciplinesDTO convertSyllabusesToDisciplinesDTO(Syllabuses syllabuses) {
        return DisciplinesDTO.builder()
                .id(syllabuses.getDisciplines().getId())
                .name(syllabuses.getDisciplines().getName())
                .disciplineGroups(convertDisciplineGroupsToDTO(syllabuses.getDisciplines().getDisciplineGroups()))
                .syllabuses(convertSyllabusesToDTO(syllabuses))
                .build();

    }

    private DisciplineGroupsDTO convertDisciplineGroupsToDTO(DisciplineGroups disciplineGroups) {
        return DisciplineGroupsDTO.builder()
                .id(disciplineGroups.getId())
                .name(disciplineGroups.getName())
                .build();
    }

    private SyllabusesDTO convertSyllabusesToDTO(Syllabuses syllabuses) {
        return SyllabusesDTO.builder()
                .id(syllabuses.getId())
                .name(syllabuses.getName())
                .status(syllabuses.getStatus())
                .build();
    }

}

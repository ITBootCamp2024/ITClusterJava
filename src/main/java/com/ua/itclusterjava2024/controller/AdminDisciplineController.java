package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.*;
import com.ua.itclusterjava2024.entity.*;
import com.ua.itclusterjava2024.service.interfaces.*;
import com.ua.itclusterjava2024.wrappers.DisciplinePageWrapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/disciplines/reviews")
public class AdminDisciplineController {
    private final SyllabusesService syllabusesService;
    private final SpecialistService specialistService;
    private final ReviewsService reviewsService;

    @Autowired
    public AdminDisciplineController(SyllabusesService syllabusesService, SpecialistService specialistService, ReviewsService reviewsService) {
        this.syllabusesService = syllabusesService;
        this.specialistService = specialistService;
        this.reviewsService = reviewsService;
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

    @PostMapping
    public ResponseEntity<DisciplinePageWrapper> create(@RequestBody @Valid ReviewDTO reviewDTO) {
        Reviews review = convertReviewDTOToEntity(reviewDTO);
        reviewsService.create(review);
        syllabusesService.updateStatus(review.getSyllabus().getId(), "Відправлено на рецензію");
        return find();
    }

    private Reviews convertReviewDTOToEntity(ReviewDTO reviewDTO) {
        return Reviews.builder()
                .specialist(new Specialist(reviewDTO.getSpecialistId()))
                .syllabus(new Syllabuses(reviewDTO.getSyllabusId()))
                .accepted(false)
                .build();
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

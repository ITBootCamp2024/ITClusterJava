package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.DisciplineBlocksDTO;
import com.ua.itclusterjava2024.dto.DisciplineGroupsDTO;
import com.ua.itclusterjava2024.dto.SpecialistDTO;
import com.ua.itclusterjava2024.entity.DisciplineBlocks;
import com.ua.itclusterjava2024.entity.DisciplineGroups;
import com.ua.itclusterjava2024.entity.Specialist;
import com.ua.itclusterjava2024.service.interfaces.ReviewsService;
import com.ua.itclusterjava2024.service.interfaces.SpecialistService;
import com.ua.itclusterjava2024.service.interfaces.SyllabusesService;
import com.ua.itclusterjava2024.wrappers.SpecialistPageWrapper;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/specialist")
public class SpecialistController {
    final SpecialistService specialistService;
    final SyllabusesService syllabusesService;
    final ReviewsService reviewsService;
    final ModelMapper modelMapper;

    public SpecialistController(SpecialistService specialistService, SyllabusesService syllabusesService, ReviewsService reviewsService, ModelMapper modelMapper) {
        this.specialistService = specialistService;
        this.syllabusesService = syllabusesService;
        this.reviewsService = reviewsService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/specialists-verified")
    public ResponseEntity<?> getVerifiedSpecialists() {
        List<SpecialistDTO> allSpecialistsSTO = specialistService.getAll().stream()
                .map(this::convertToDTO)
                .toList();
        List<Specialist> allSpecialists = specialistService.getAll();

        // Створення об'єкта респонсу
        Map<String, Object> specialistData = new HashMap<>();

        // Для кожного спеціаліста отримуємо кількість запропонованих та взятих на валідацію силабусів
        for (SpecialistDTO specialist : allSpecialistsSTO) {
            long proposedSyllabusesCount = reviewsService.getAllReviewsCount(specialist.getId());
            long syllabusesForValidationCount = reviewsService.getAllReviewsCountAcceptedTrue(specialist.getId());

            specialistData.put("proposed_syllabuses_count", proposedSyllabusesCount);
            specialistData.put("syllabuses_for_validation_count", syllabusesForValidationCount);
            specialistData.put("specialist", specialist);
        }
        SpecialistPageWrapper response = new SpecialistPageWrapper(specialistData);
        return ResponseEntity.ok(response);
    }

    private DisciplineGroups convertToEntity(DisciplineGroupsDTO disciplineGroupsDTO) {
        DisciplineGroups disciplineGroups = modelMapper.map(disciplineGroupsDTO, DisciplineGroups.class);
        if (disciplineGroupsDTO.getBlock() != null) {
            disciplineGroups.setBlock_id(modelMapper.map(disciplineGroupsDTO.getBlock(), DisciplineBlocks.class));
        }
        return disciplineGroups;
    }

    private SpecialistDTO convertToDTO(Specialist specialist) {
        return SpecialistDTO.builder()
                .id(specialist.getId())
                .name(specialist.getName())
                .position(specialist.getPosition())
                .company(specialist.getCompany())
                .email(specialist.getEmail())
                .verified(specialist.getVerified())
                .build();
    }
}

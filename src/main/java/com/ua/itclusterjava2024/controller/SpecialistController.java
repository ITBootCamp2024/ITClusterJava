package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.entity.Specialist;
import com.ua.itclusterjava2024.service.interfaces.ReviewsService;
import com.ua.itclusterjava2024.service.interfaces.SpecialistService;
import com.ua.itclusterjava2024.service.interfaces.SyllabusesService;
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

    public SpecialistController(SpecialistService specialistService, SyllabusesService syllabusesService, ReviewsService reviewsService) {
        this.specialistService = specialistService;
        this.syllabusesService = syllabusesService;
        this.reviewsService = reviewsService;
    }


    @GetMapping("/specialists-verified")
    public ResponseEntity<?> getVerifiedSpecialists() {
        List<Specialist> allSpecialists = specialistService.getAll();
        long verifiedCount = allSpecialists.stream().filter(Specialist::getVerified).count();
        long notVerifiedCount = allSpecialists.size() - verifiedCount;

        // Створення об'єкта респонсу
        Map<String, Object> response = new HashMap<>();
        response.put("verified_count", verifiedCount);
        response.put("not_verified_count", notVerifiedCount);

        // Для кожного спеціаліста отримуємо кількість запропонованих та взятих на валідацію силабусів
        for (Specialist specialist : allSpecialists) {
            long proposedSyllabusesCount = reviewsService.getAllReviewsCount(specialist.getId());
            long syllabusesForValidationCount = reviewsService.getAllReviewsCountAcceptedTrue(specialist.getId());

            Map<String, Object> specialistData = new HashMap<>();
            specialistData.put("specialist", specialist);
            specialistData.put("proposed_syllabuses_count", proposedSyllabusesCount);
            specialistData.put("syllabuses_for_validation_count", syllabusesForValidationCount);

            response.put("specialist_" + specialist.getId(), specialistData);
        }

        return ResponseEntity.ok(response);
    }
}

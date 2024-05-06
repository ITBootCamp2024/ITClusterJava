package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.SyllabusReviewDTO;
import com.ua.itclusterjava2024.entity.Syllabuses;
import com.ua.itclusterjava2024.service.interfaces.ReviewsService;
import com.ua.itclusterjava2024.service.interfaces.SyllabusesService;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/syllabuses")
public class SyllabusesSpecialistController {

    private final SyllabusesService syllabusesService;
    private final ReviewsService reviewsService;

    @GetMapping("/proposed/{specialist_id}")
    public PageWrapper<SyllabusReviewDTO> getProposedSyllabuses(@PathVariable("specialist_id") Long specialistId) {
        List<Syllabuses> proposedSyllabuses = syllabusesService.findSyllabusesBySpecialistId(specialistId, false);

        List<SyllabusReviewDTO> syllabusDTOS = proposedSyllabuses.stream()
                .map(syllabus -> mapToSyllabusReviewDTO(syllabus, false))
                .toList();

        return new PageWrapper<>(syllabusDTOS, proposedSyllabuses.size());
    }

    @GetMapping("/refereed/{specialist_id}")
    public PageWrapper<SyllabusReviewDTO> getRefereedSyllabuses(@PathVariable("specialist_id") Long specialistId) {
        List<Syllabuses> proposedSyllabuses = syllabusesService.findSyllabusesBySpecialistId(specialistId, true);

        List<SyllabusReviewDTO> syllabusDTOS = proposedSyllabuses.stream()
                .map(syllabuses -> mapToSyllabusReviewDTO(syllabuses, true))
                .toList();

        return new PageWrapper<>(syllabusDTOS, proposedSyllabuses.size());
    }

    @PatchMapping("/proposed/{specialist_id}")
    public PageWrapper<SyllabusReviewDTO> updateSyllabusAcceptance(
            @PathVariable("specialist_id") Long specialistId,
            @RequestParam("syllabus_id") Long syllabusId) {

        if (Boolean.FALSE.equals(syllabusesService.existsById(syllabusId))) {
            throw new EntityNotFoundException("Syllabus with id " + syllabusId + " not found");
        }
        syllabusesService.updateStatus(syllabusId, "На рецензії");
        reviewsService.updateAcceptedBySpecialistIdAndSyllabusId(specialistId, syllabusId, true);
        return getProposedSyllabuses(specialistId);
    }


    private SyllabusReviewDTO mapToSyllabusReviewDTO(Syllabuses syllabus, Boolean accepted) {
        return SyllabusReviewDTO.builder()
                .syllabusId(syllabus.getId())
                .discipline(syllabus.getDisciplines().getName())
                .disciplineBlock(syllabus.getDisciplines().getDisciplineGroups().getDisciplineBlocks().getName())
                .accepted(accepted)
                .build();
    }

}

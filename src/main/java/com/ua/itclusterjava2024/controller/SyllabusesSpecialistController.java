package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.AnswerDTO;
import com.ua.itclusterjava2024.dto.SyllabusAnswersDTO;
import com.ua.itclusterjava2024.dto.SyllabusReviewDTO;
import com.ua.itclusterjava2024.entity.Answer;
import com.ua.itclusterjava2024.entity.Reviews;
import com.ua.itclusterjava2024.entity.Syllabuses;
import com.ua.itclusterjava2024.service.interfaces.AnswerService;
import com.ua.itclusterjava2024.service.interfaces.ReviewsService;
import com.ua.itclusterjava2024.service.interfaces.SyllabusesService;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/syllabuses")
public class SyllabusesSpecialistController {

    private final SyllabusesService syllabusesService;
    private final ReviewsService reviewsService;
    private final AnswerService answerService;
    private final ModelMapper modelMapper;

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
        List<Syllabuses> acceptedSyllabuses = syllabusesService.findSyllabusesBySpecialistId(specialistId, true);

        List<SyllabusReviewDTO> syllabusDTOS = acceptedSyllabuses.stream()
                .map(syllabuses -> mapToSyllabusReviewDTO(syllabuses, true))
                .toList();

        return new PageWrapper<>(syllabusDTOS, acceptedSyllabuses.size());
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


    @GetMapping("/answers/{specialist_id}")
    public PageWrapper<SyllabusAnswersDTO> getSyllabusAnswers(@PathVariable("specialist_id") Long specialistId) {
        List<Reviews> acceptedReviews = reviewsService.findAcceptedBySpecialistId(specialistId);

        List<SyllabusAnswersDTO> syllabusAnswersDTOS = acceptedReviews.stream()
                .map(review -> {
                    List<AnswerDTO> answerDTOS = answerService.findAllByReviewId(review.getId())
                            .stream()
                            .map(answer -> modelMapper.map(answer, AnswerDTO.class))
                            .toList();

                    return new SyllabusAnswersDTO(review.getSyllabus().getId(), answerDTOS);
                })
                .toList();

        return new PageWrapper<>(syllabusAnswersDTOS, acceptedReviews.size());
    }

    @PostMapping("/answers/{specialist_id}")
    public PageWrapper<SyllabusAnswersDTO> setSyllabusAnswers(@PathVariable("specialist_id") Long specialistId, @RequestBody AnswerDTO answerDTO) {
        Reviews review = reviewsService.findAcceptedBySpecialistIdAndSyllabusId(specialistId, answerDTO.getSyllabusId())
                .orElseThrow(() -> new EntityNotFoundException("Прийнятий відгук не знайдено для фахівця " + specialistId + " та сілабусу " + answerDTO.getSyllabusId()));

        Answer answer = modelMapper.map(answerDTO, Answer.class);
        answer.setReview(review);
        answerService.create(answer);

        if (answerService.countAllByReviewId(review.getId()) >= 10) {
            syllabusesService.updateStatus(answerDTO.getSyllabusId(), "Рецензовано");
        }
        return getSyllabusAnswers(specialistId);
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

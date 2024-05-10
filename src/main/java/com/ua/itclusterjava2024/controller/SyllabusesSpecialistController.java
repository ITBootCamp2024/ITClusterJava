package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.AnswersDTO;
import com.ua.itclusterjava2024.dto.SyllabusAnswersDTO;
import com.ua.itclusterjava2024.dto.SyllabusReviewDTO;
import com.ua.itclusterjava2024.dto.request.AnswersRequest;
import com.ua.itclusterjava2024.dto.request.DeleteReviewRequest;
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
        syllabusesService.updateStatus(syllabusId, "Прийнято на рецензування");
        reviewsService.updateAcceptedBySpecialistIdAndSyllabusId(specialistId, syllabusId, true);
        return getProposedSyllabuses(specialistId);
    }

    @DeleteMapping("/proposed/{specialist_id}")
    public PageWrapper<SyllabusReviewDTO> deleteReview(@PathVariable("specialist_id") Long specialistId, @RequestParam("syllabus_id") Long syllabus_id) {
        answerService.deleteAllByReviewId(reviewsService
                .findBySpecialistIdAndSyllabusId(specialistId, syllabus_id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found")).getId());
        reviewsService.deleteBySpecialistIdAndSyllabusId(specialistId, syllabus_id);
        syllabusesService.updateStatus(syllabus_id, "Заповнено");
        return getProposedSyllabuses(specialistId);
    }

    @GetMapping("/answers/{specialist_id}")
    public PageWrapper<SyllabusAnswersDTO> getSyllabusAnswers(@PathVariable("specialist_id") Long specialistId) {
        List<Reviews> acceptedReviews = reviewsService.findAcceptedBySpecialistId(specialistId);

        List<SyllabusAnswersDTO> syllabusAnswersDTOS = acceptedReviews.stream()
                .map(review -> {
                    List<AnswersDTO> answersDTOS = answerService.findAllByReviewId(review.getId())
                            .stream()
                            .map(answer -> modelMapper.map(answer, AnswersDTO.class))
                            .toList();

                    return new SyllabusAnswersDTO(review.getSyllabus().getId(), answersDTOS);
                })
                .toList();

        return new PageWrapper<>(syllabusAnswersDTOS, acceptedReviews.size());
    }

    @PostMapping("/answers/{specialist_id}")
    public PageWrapper<SyllabusAnswersDTO> setSyllabusAnswers(@PathVariable("specialist_id") Long specialistId, @RequestBody AnswersRequest answersRequest) {
        Reviews review = reviewsService.findAcceptedBySpecialistIdAndSyllabusId(specialistId, answersRequest.getSyllabusId())
                .orElseThrow(() -> new EntityNotFoundException("Прийнятий відгук не знайдено для фахівця " + specialistId + " та сілабусу " + answersRequest.getSyllabusId()));

        for (AnswersDTO answersDTO : answersRequest.getAnswers()) {
            Answer answer = modelMapper.map(answersDTO, Answer.class);
            answer.setReview(review);
            Answer answerId=answerService.findByReviewIdAndTableNumberAndQuestionNumber(review.getId(),
                    answersDTO.getTableNumber(),answersDTO.getQuestionNumber());
            if(answerId!=null){
                answer.setId(answerId.getId());
            }
            answerService.create(answer);
        }

        if (answerService.countAllByReviewId(review.getId()) >= 10) {
            syllabusesService.updateStatus(answersRequest.getSyllabusId(), "Рецензовано");
        }
        return getSyllabusAnswers(specialistId);
    }


    private SyllabusReviewDTO mapToSyllabusReviewDTO(Syllabuses syllabus, Boolean accepted) {
        return SyllabusReviewDTO.builder()
                .syllabusId(syllabus.getId())
                .discipline(syllabus.getDisciplines().getName())
                .disciplineBlock(syllabus.getDisciplines().getDisciplineGroups().getDisciplineBlocks().getName())
                .status(syllabus.getStatus())
                .accepted(accepted)
                .build();
    }

}

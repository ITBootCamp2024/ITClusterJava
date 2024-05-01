package com.ua.itclusterjava2024.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ua.itclusterjava2024.dto.AssessmentDTO;
import com.ua.itclusterjava2024.dto.GraduateTaskDTO;
import com.ua.itclusterjava2024.dto.SyllabusesDTO;
import com.ua.itclusterjava2024.entity.Assessment;
import com.ua.itclusterjava2024.entity.GraduateTask;
import com.ua.itclusterjava2024.entity.Syllabuses;
import com.ua.itclusterjava2024.service.interfaces.AssessmentService;
import com.ua.itclusterjava2024.service.interfaces.SyllabusesService;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/syllabuses/assessments")
public class SyllabusesAssessmentsController {
    private final SyllabusesService syllabusesService;
    private final AssessmentService assessmentService;
    private final ModelMapper modelMapper;
    private final EntityManager entityManager;

    public SyllabusesAssessmentsController(SyllabusesService syllabusesService, AssessmentService assessmentService, ModelMapper modelMapper, EntityManager entityManager) {
        this.syllabusesService = syllabusesService;
        this.assessmentService = assessmentService;
        this.modelMapper = modelMapper;
        this.entityManager = entityManager;
    }


    @GetMapping("/{syllabus_id}")
    public ResponseEntity<?> getAssessmentsBySyllabusId(@PathVariable("syllabus_id") Long syllabusId) {
        List<AssessmentDTO> assessmentsDTO = assessmentService.getAllAssessmentsBySyllabus(syllabusId)
                .stream().map(this::convertToDTO)
                .toList();
        Map<String, Object> response = Map.of(
                "content", Map.of(
                        "syllabus_id", syllabusId,
                        "assessments", assessmentsDTO
                )
        );
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Доступно авторизованим користувачам з ролями teacher, admin, content_manager")
    @PreAuthorize("hasAnyAuthority('teacher', 'admin', 'content_manager')")
    @PostMapping("/{syllabus_id}")
    public ResponseEntity<?> createAssessments(@PathVariable("syllabus_id") Long syllabusId, @RequestBody List<AssessmentDTO> assessments) {
        List<Assessment> assessmentsList = assessments.stream()
                .map(this::convertToEntity)
                .toList();
        Map<String, Object> response = Map.of(
                "content", Map.of(
                        "syllabus_id", syllabusId,
                        "assessments", assessmentService.createAllAssessmentsWithSyllabus(assessmentsList, syllabusId)
                )
        );
        return ResponseEntity.ok(response);
    }

    private AssessmentDTO convertToDTO(Assessment assessment) {
        return modelMapper.map(assessment, AssessmentDTO.class);
    }

    private Assessment convertToEntity(AssessmentDTO assessmentDTO) {
        return modelMapper.map(assessmentDTO, Assessment.class);
    }

    private static String getToken(String authorizationHeader) {
        return authorizationHeader.substring("Bearer ".length());
    }
}

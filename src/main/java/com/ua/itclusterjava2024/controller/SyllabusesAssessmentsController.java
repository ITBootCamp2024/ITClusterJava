package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.AssessmentDTO;
import com.ua.itclusterjava2024.entity.Assessment;
import com.ua.itclusterjava2024.service.interfaces.AssessmentService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/syllabuses/assessments")
public class SyllabusesAssessmentsController {
    private final AssessmentService assessmentService;
    private final ModelMapper modelMapper;

    public SyllabusesAssessmentsController(AssessmentService assessmentService, ModelMapper modelMapper) {
        this.assessmentService = assessmentService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/{syllabus_id}")
    public ResponseEntity<Map<String, Object>> getAssessmentsBySyllabusId(@PathVariable("syllabus_id") Long syllabusId) {
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
    public ResponseEntity<Map<String, Object>> createAssessments(@PathVariable("syllabus_id") Long syllabusId, @RequestBody List<AssessmentDTO> assessments) {
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
}

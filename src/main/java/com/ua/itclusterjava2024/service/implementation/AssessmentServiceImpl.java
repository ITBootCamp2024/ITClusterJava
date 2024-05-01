package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.Assessment;
import com.ua.itclusterjava2024.repository.AssessmentRepository;
import com.ua.itclusterjava2024.repository.UserRepository;
import com.ua.itclusterjava2024.service.interfaces.AssessmentService;
import com.ua.itclusterjava2024.service.interfaces.SyllabusesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssessmentServiceImpl implements AssessmentService {
    private final AssessmentRepository assessmentRepository;
    private final SyllabusesService syllabusesService;

    public AssessmentServiceImpl(AssessmentRepository assessmentRepository, SyllabusesService syllabusesService) {
        this.assessmentRepository = assessmentRepository;
        this.syllabusesService = syllabusesService;
    }

    @Override
    public Assessment create(Assessment specialty) {
        return assessmentRepository.save(specialty);
    }

    @Override
    public Optional<Assessment> readById(long id) {
        return assessmentRepository.findById(id);
    }

    @Override
    public Assessment update(long id, Assessment assessment) {
        assessment.setId(id);
        return assessmentRepository.save(assessment);
    }

    @Override
    public void delete(long id) {
        assessmentRepository.deleteById(id);
    }

    @Override
    public List<Assessment> getAll() {
        return assessmentRepository.findAll();
    }

    @Override
    public Page<Assessment> getAll(Pageable pageable) {
        return assessmentRepository.findAll(pageable);
    }

    public List<Assessment> getAllAssessmentsBySyllabus(long syllabusId) {
        return assessmentRepository.findAllBySyllabusId(syllabusId);
    }

//    public List<Assessment> createAllAssessmentsWithSyllabus(List<Assessment> assessments, long syllabusId) {
//
//    }
}

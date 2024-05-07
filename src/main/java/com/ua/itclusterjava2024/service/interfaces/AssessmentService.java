package com.ua.itclusterjava2024.service.interfaces;

import com.ua.itclusterjava2024.entity.Assessment;

import java.util.List;

public interface AssessmentService extends Service<Assessment> {
    List<Assessment> getAllAssessmentsBySyllabus(long syllabusId);

    List<Assessment> createAllAssessmentsWithSyllabus(List<Assessment> assessments, long syllabusId);
}

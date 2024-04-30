package com.ua.itclusterjava2024.service.interfaces;

import com.ua.itclusterjava2024.entity.Assessment;

import java.util.List;

public interface AssessmentService extends Service<Assessment>{
    public List<Assessment> getAllAssessmentsBySyllabus(long syllabusId);
}

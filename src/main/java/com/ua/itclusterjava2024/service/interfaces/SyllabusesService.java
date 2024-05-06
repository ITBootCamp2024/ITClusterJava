package com.ua.itclusterjava2024.service.interfaces;

import com.ua.itclusterjava2024.entity.Syllabuses;

import java.util.List;

public interface SyllabusesService extends Service<Syllabuses>{
    List<Syllabuses> findByDisciplineId(Long disciplineId);
    List<Syllabuses> findSyllabusesBySpecialistId(Long specialistId, boolean accepted);
    List<Syllabuses> findSyllabusesByTeacherId(Long teacherId, boolean accepted);

    Boolean existsById(Long id);
    void updateStatus(Long id, String status);
}

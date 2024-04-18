package com.ua.itclusterjava2024.service.interfaces;

import com.ua.itclusterjava2024.entity.Disciplines;
import com.ua.itclusterjava2024.entity.Syllabuses;

import java.util.List;

public interface SyllabusesService extends Service<Syllabuses>{
    List<Syllabuses> findByDisciplineId(Long disciplineId);
}

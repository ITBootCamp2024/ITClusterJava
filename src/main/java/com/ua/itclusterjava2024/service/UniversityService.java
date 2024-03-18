package com.ua.itclusterjava2024.service;

import com.ua.itclusterjava2024.entity.Programs;
import com.ua.itclusterjava2024.entity.University;

import java.util.List;

public interface UniversityService {
    University create(University university);
    University readById(long id);
    University update(University university);
    void delete(long id);
    List<University> getAll();
}

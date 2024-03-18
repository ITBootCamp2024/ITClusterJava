package com.ua.itclusterjava2024.service;

import com.ua.itclusterjava2024.entity.Specialty;
import com.ua.itclusterjava2024.entity.University;

import java.util.List;

public interface SpecialtyService {
    Specialty create(Specialty specialty);
    Specialty readById(long id);
    Specialty update(Specialty specialty);
    void delete(long id);
    List<Specialty> getAll();
}

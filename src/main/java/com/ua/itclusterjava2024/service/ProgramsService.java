package com.ua.itclusterjava2024.service;

import com.ua.itclusterjava2024.entity.Programs;

import java.util.List;

public interface ProgramsService {
    Programs create(Programs programs);
    Programs readById(long id);
    Programs update(Programs program);
    void delete(long id);
    List<Programs> getAll();
}

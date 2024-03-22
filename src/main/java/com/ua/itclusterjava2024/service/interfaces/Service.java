package com.ua.itclusterjava2024.service.interfaces;

import com.ua.itclusterjava2024.entity.Specialty;

import java.util.List;

public interface Service <T>{
    T create(T specialty);
    T readById(long id);
    T update(long id, T t);
    void delete(long id);
    List<T> getAll();
}

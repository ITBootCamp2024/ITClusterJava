package com.ua.itclusterjava2024.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface Service<T> {

    T create(T entity);

    Optional<T> readById(long id);

    T update(long id, T t);

    void delete(long id);

    List<T> getAll();

    Page<T> getAll(Pageable pageable);
}

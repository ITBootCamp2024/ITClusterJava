package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.Specialty;
import com.ua.itclusterjava2024.repository.SpecialtyRepository;
import com.ua.itclusterjava2024.service.interfaces.SpecialtyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    public SpecialtyServiceImpl(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Specialty create(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public Specialty readById(long id) {
        Optional<Specialty> foundSchool = specialtyRepository.findById(id);
        return foundSchool.orElse(null);
    }

    @Override
    public Specialty update(long id, Specialty t) {
        t.setId(id);
        return specialtyRepository.save(t);
    }

    @Override
    public void delete(long id) {
        specialtyRepository.deleteById(id);
    }

    @Override
    public List<Specialty> getAll() {
        return specialtyRepository.findAll();
    }

    @Override
    public Page<Specialty> getAll(Pageable pageable) {
        return specialtyRepository.findAll(pageable);
    }
}

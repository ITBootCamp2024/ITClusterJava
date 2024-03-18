package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.Specialty;
import com.ua.itclusterjava2024.repository.SpecialtyRepository;
import com.ua.itclusterjava2024.service.SpecialtyService;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return specialtyRepository.getById(id);
    }

    @Override
    public Specialty update(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public void delete(long id) {
        specialtyRepository.deleteById(id);
    }

    @Override
    public List<Specialty> getAll() {
        return specialtyRepository.findAll();
    }
}

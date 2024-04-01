package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.Disciplines;
import com.ua.itclusterjava2024.repository.DisciplineRepository;
import com.ua.itclusterjava2024.service.interfaces.DisciplineService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinesServiceImpl implements DisciplineService {

    private final DisciplineRepository disciplineRepository;

    public DisciplinesServiceImpl(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    @Override
    public Disciplines create(Disciplines disciplines) {
        return disciplineRepository.save(disciplines);
    }

    @Override
    public Optional<Disciplines> readById(long id) {
        return disciplineRepository.findById(id);
    }

    @Override
    public Disciplines update(long id, Disciplines disciplines) {
        disciplines.setId(id);
        return disciplineRepository.save(disciplines);
    }

    @Override
    public void delete(long id) {
        disciplineRepository.deleteById(id);
    }

    @Override
    public List<Disciplines> getAll() {
        return disciplineRepository.findAll();
    }

    @Override
    public Page<Disciplines> getAll(Pageable pageable) {
        return disciplineRepository.findAll(pageable);
    }
}

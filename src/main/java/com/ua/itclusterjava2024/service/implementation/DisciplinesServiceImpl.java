package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.Disciplines;
import com.ua.itclusterjava2024.repository.DisciplinesRepository;
import com.ua.itclusterjava2024.service.interfaces.DisciplinesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinesServiceImpl implements DisciplinesService {

    private final DisciplinesRepository disciplinesRepository;

    public DisciplinesServiceImpl(DisciplinesRepository disciplinesRepository) {
        this.disciplinesRepository = disciplinesRepository;
    }

    @Override
    public Disciplines create(Disciplines disciplines) {
        return disciplinesRepository.save(disciplines);
    }

    @Override
    public Optional<Disciplines> readById(long id) {
        return disciplinesRepository.findById(id);
    }

    @Override
    public Disciplines update(long id, Disciplines disciplines) {
        // а тут виходить що ми ще раз сетаємо id, хоча воно вже є в об'єкті
        disciplines.setId(id);
        return disciplinesRepository.save(disciplines);
    }

    @Override
    public void delete(long id) {
        disciplinesRepository.deleteById(id);
    }

    @Override
    public List<Disciplines> getAll() {
        return disciplinesRepository.findAll();
    }

    @Override
    public Page<Disciplines> getAll(Pageable pageable) {
        return disciplinesRepository.findAll(pageable);
    }
}

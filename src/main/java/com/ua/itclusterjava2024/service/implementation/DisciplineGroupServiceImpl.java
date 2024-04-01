package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.DisciplineGroup;
import com.ua.itclusterjava2024.repository.DisciplineGroupRepository;
import com.ua.itclusterjava2024.service.interfaces.DisciplineGroupService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplineGroupServiceImpl implements DisciplineGroupService {

    private final DisciplineGroupRepository disciplineGroupRepository;

    public DisciplineGroupServiceImpl(DisciplineGroupRepository disciplineGroupRepository) {
        this.disciplineGroupRepository = disciplineGroupRepository;
    }

    @Override
    public DisciplineGroup create(DisciplineGroup disciplineGroup) {
        return disciplineGroupRepository.save(disciplineGroup);
    }

    @Override
    public Optional<DisciplineGroup> readById(long id) {
        return disciplineGroupRepository.findById(id);
    }

    @Override
    public DisciplineGroup update(long id, DisciplineGroup disciplineGroup) {
        disciplineGroup.setId(id);
        return disciplineGroupRepository.save(disciplineGroup);
    }

    @Override
    public void delete(long id) {
        disciplineGroupRepository.deleteById(id);
    }

    @Override
    public List<DisciplineGroup> getAll() {
        return disciplineGroupRepository.findAll();
    }

    @Override
    public Page<DisciplineGroup> getAll(Pageable pageable) {
        return disciplineGroupRepository.findAll(pageable);
    }
}

package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.DisciplineGroups;
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
    public DisciplineGroups create(DisciplineGroups disciplineGroups) {
        return disciplineGroupRepository.save(disciplineGroups);
    }

    @Override
    public Optional<DisciplineGroups> readById(long id) {
        return disciplineGroupRepository.findById(id);
    }

    @Override
    public DisciplineGroups update(long id, DisciplineGroups disciplineGroups) {
        disciplineGroups.setId(id);
        return disciplineGroupRepository.save(disciplineGroups);
    }

    @Override
    public void delete(long id) {
        disciplineGroupRepository.deleteById(id);
    }

    @Override
    public List<DisciplineGroups> getAll() {
        return disciplineGroupRepository.findAll();
    }

    @Override
    public Page<DisciplineGroups> getAll(Pageable pageable) {
        return disciplineGroupRepository.findAll(pageable);
    }
}

package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.DisciplineGroups;
import com.ua.itclusterjava2024.repository.DisciplineGroupsRepository;
import com.ua.itclusterjava2024.service.interfaces.DisciplineGroupService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplineGroupServiceImpl implements DisciplineGroupService {

    private final DisciplineGroupsRepository disciplineGroupsRepository;

    public DisciplineGroupServiceImpl(DisciplineGroupsRepository disciplineGroupsRepository) {
        this.disciplineGroupsRepository = disciplineGroupsRepository;
    }

    @Override
    public DisciplineGroups create(DisciplineGroups disciplineGroups) {
        return disciplineGroupsRepository.save(disciplineGroups);
    }

    @Override
    public Optional<DisciplineGroups> readById(long id) {
        return disciplineGroupsRepository.findById(id);
    }

    @Override
    public DisciplineGroups update(long id, DisciplineGroups disciplineGroups) {
        disciplineGroups.setId(id);
        return disciplineGroupsRepository.save(disciplineGroups);
    }

    @Override
    public void delete(long id) {
        disciplineGroupsRepository.deleteById(id);
    }

    @Override
    public List<DisciplineGroups> getAll() {
        return disciplineGroupsRepository.findAll();
    }

    @Override
    public Page<DisciplineGroups> getAll(Pageable pageable) {
        return disciplineGroupsRepository.findAll(pageable);
    }
}

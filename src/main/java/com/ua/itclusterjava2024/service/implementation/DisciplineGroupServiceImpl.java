package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.DisciplineGroup;
import com.ua.itclusterjava2024.repository.CourseGroupRepository;
import com.ua.itclusterjava2024.service.interfaces.DisciplineGroupService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplineGroupServiceImpl implements DisciplineGroupService {

    private final CourseGroupRepository courseGroupRepository;

    public DisciplineGroupServiceImpl(CourseGroupRepository courseGroupRepository) {
        this.courseGroupRepository = courseGroupRepository;
    }

    @Override
    public DisciplineGroup create(DisciplineGroup disciplineGroup) {
        return courseGroupRepository.save(disciplineGroup);
    }

    @Override
    public Optional<DisciplineGroup> readById(long id) {
        return courseGroupRepository.findById(id);
    }

    @Override
    public DisciplineGroup update(long id, DisciplineGroup disciplineGroup) {
        disciplineGroup.setId(id);
        return courseGroupRepository.save(disciplineGroup);
    }

    @Override
    public void delete(long id) {
        courseGroupRepository.deleteById(id);
    }

    @Override
    public List<DisciplineGroup> getAll() {
        return courseGroupRepository.findAll();
    }

    @Override
    public Page<DisciplineGroup> getAll(Pageable pageable) {
        return courseGroupRepository.findAll(pageable);
    }
}

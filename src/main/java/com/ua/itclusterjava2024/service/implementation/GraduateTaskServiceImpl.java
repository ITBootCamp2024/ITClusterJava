package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.GraduateTask;
import com.ua.itclusterjava2024.repository.GraduateTaskRepository;
import com.ua.itclusterjava2024.service.interfaces.GraduateTaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GraduateTaskServiceImpl implements GraduateTaskService {

    private final GraduateTaskRepository graduateTaskRepository;

    public GraduateTaskServiceImpl(GraduateTaskRepository graduateTaskRepository) {
        this.graduateTaskRepository = graduateTaskRepository;
    }

    @Override
    public List<GraduateTask> findAllBySyllabusId(Long syllabusId) {
        return graduateTaskRepository.findAllBySyllabusId(syllabusId);
    }

    @Override
    public GraduateTask create(GraduateTask specialty) {
        return graduateTaskRepository.save(specialty);
    }

    @Override
    public Optional<GraduateTask> readById(long id) {
        return graduateTaskRepository.findById(id);
    }

    @Override
    public GraduateTask update(long id, GraduateTask graduateTask) {
        graduateTask.setId(id);
        return graduateTaskRepository.save(graduateTask);
    }

    @Override
    public void delete(long id) {
        graduateTaskRepository.deleteById(id);
    }

    @Override
    public List<GraduateTask> getAll() {
        return graduateTaskRepository.findAll();
    }

    @Override
    public Page<GraduateTask> getAll(Pageable pageable) {
        return graduateTaskRepository.findAll(pageable);
    }
}

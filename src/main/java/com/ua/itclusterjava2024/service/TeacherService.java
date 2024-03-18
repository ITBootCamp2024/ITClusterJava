package com.ua.itclusterjava2024.service;

import com.ua.itclusterjava2024.entity.Teacher;
import com.ua.itclusterjava2024.repositories.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TeacherService {
    private final TeacherRepository repository;

    public TeacherService(TeacherRepository repository) {
        this.repository = repository;
    }

    public List<Teacher> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void update(int id, Teacher updatedTeacher) {
        updatedTeacher.setId(id);
        repository.save(updatedTeacher);
    }

    @Transactional
    public void add(Teacher teacher) {
        repository.save(teacher);
    }
}

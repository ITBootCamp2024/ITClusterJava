package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.Teachers;
import com.ua.itclusterjava2024.repository.TeachersRepository;
import com.ua.itclusterjava2024.service.interfaces.TeachersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TeachersServiceImpl implements TeachersService {
    private final TeachersRepository repository;

    public TeachersServiceImpl(TeachersRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public Teachers create(Teachers teachers) {
        return repository.save(teachers);
    }

    @Override
    public Teachers readById(long id) {
        return repository.getById(id);
    }

    @Transactional
    @Override
    public Teachers update(Teachers t) {
        return repository.save(t);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Teachers> getAll() {
        return repository.findAll();
    }
}

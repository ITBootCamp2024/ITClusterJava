package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.Teachers;
import com.ua.itclusterjava2024.repository.TeachersRepository;
import com.ua.itclusterjava2024.service.interfaces.TeachersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeachersServiceImpl implements TeachersService {
    private final TeachersRepository teachersRepository;

    public TeachersServiceImpl(TeachersRepository teachersRepository) {
        this.teachersRepository = teachersRepository;
    }

    @Override
    public Teachers create(Teachers teachers) {
        return teachersRepository.save(teachers);
    }

    @Override
    public Teachers readById(long id) {
        Optional<Teachers> foundSchool = teachersRepository.findById(id);
        return foundSchool.orElse(null);
    }

    @Override
    public Teachers update(long id, Teachers t) {
        t.setId(id);
        return teachersRepository.save(t);
    }

    @Override
    public void delete(long id) {
        teachersRepository.deleteById(id);
    }

    @Override
    public List<Teachers> getAll() {
        return teachersRepository.findAll();
    }

    @Override
    public Page<Teachers> getAll(Pageable pageable) {
        return teachersRepository.findAll(pageable);
    }
}

package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.School;
import com.ua.itclusterjava2024.repository.SchoolRepository;
import com.ua.itclusterjava2024.service.interfaces.SchoolService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository schoolRepository;

    public SchoolServiceImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Transactional
    @Override
    public School create(School school) {
        return schoolRepository.save(school);
    }

    @Override
    public School readById(long id) {
        return schoolRepository.getById(id);
    }

    @Transactional
    @Override
    public School update(School school) {
        return schoolRepository.save(school);
    }

    @Override
    public void delete(long id) {
        schoolRepository.deleteById(id);
    }

    @Override
    public List<School> getAll() {
        return schoolRepository.findAll();
    }
}

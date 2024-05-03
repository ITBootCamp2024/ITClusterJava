package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.BaseInformationSyllabus;
import com.ua.itclusterjava2024.repository.BaseInformationSyllabusRepository;
import com.ua.itclusterjava2024.service.interfaces.SyllabusesBaseInfoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SyllabusesBaseInfoServiceImpl implements SyllabusesBaseInfoService {
    private final BaseInformationSyllabusRepository repository;

    public SyllabusesBaseInfoServiceImpl(BaseInformationSyllabusRepository repository) {
        this.repository = repository;
    }

    @Override
    public BaseInformationSyllabus create(BaseInformationSyllabus specialty) {
        return repository.save(specialty);
    }

    @Override
    public Optional<BaseInformationSyllabus> readById(long id) {
        return repository.findById(id);
    }

    @Override
    public BaseInformationSyllabus update(long id, BaseInformationSyllabus baseInformationSyllabus) {
        baseInformationSyllabus.setId(id);
        return repository.save(baseInformationSyllabus);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public List<BaseInformationSyllabus> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<BaseInformationSyllabus> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<BaseInformationSyllabus> getBaseInfoBySyllabus(long syllabusId) {
        return repository.findBySyllabusId(syllabusId);
    }
}

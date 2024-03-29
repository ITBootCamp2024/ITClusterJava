package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.Degree;
import com.ua.itclusterjava2024.entity.Degree;
import com.ua.itclusterjava2024.repository.DegreeRepository;
import com.ua.itclusterjava2024.service.interfaces.DegreeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DegreeServiceImpl implements DegreeService {
    private final DegreeRepository degreeRepository;

    public DegreeServiceImpl(DegreeRepository degreeRepository) {
        this.degreeRepository = degreeRepository;
    }

    @Override
    public Degree create(Degree degree) {
        return degreeRepository.save(degree);
    }

    @Override
    public Degree readById(long id) {
        Optional<Degree> foundSchool = degreeRepository.findById(id);
        return foundSchool.orElse(null);
    }

    @Override
    public Degree update(long id, Degree degree) {
        degree.setId(id);
        return degreeRepository.save(degree);
    }

    @Override
    public void delete(long id) {
        degreeRepository.deleteById(id);
    }

    @Override
    public Page<Degree> getAll(Pageable pageable) {
        return degreeRepository.findAll(pageable);
    }

    @Override
    public List<Degree> getAll() {
        return degreeRepository.findAll();
    }
}

package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.EducationPrograms;
import com.ua.itclusterjava2024.repository.EducationProgramsRepository;
import com.ua.itclusterjava2024.service.interfaces.EducationProgramsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationProgramsServiceImpl implements EducationProgramsService {

    private final EducationProgramsRepository educationProgramsRepository;

    public EducationProgramsServiceImpl(EducationProgramsRepository educationProgramsRepository) {
        this.educationProgramsRepository = educationProgramsRepository;
    }

    @Override
    public EducationPrograms create(EducationPrograms educationPrograms) {
        return educationProgramsRepository.save(educationPrograms);
    }

    @Override
    public Optional<EducationPrograms> readById(long id) {
        return educationProgramsRepository.findById(id);
    }

    @Override
    public EducationPrograms update(long id, EducationPrograms t) {
        t.setId(id);
        return educationProgramsRepository.save(t);
    }

    @Override
    public void delete(long id) {
        educationProgramsRepository.deleteById(id);
    }

    @Override
    public Page<EducationPrograms> getAll(Pageable pageable) {
        return educationProgramsRepository.findAll(pageable);    }

    @Override
    public List<EducationPrograms> getAll() {
        return educationProgramsRepository.findAll();
    }
}

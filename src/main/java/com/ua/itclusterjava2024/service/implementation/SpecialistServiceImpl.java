package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.Specialist;
import com.ua.itclusterjava2024.repository.SpecialistRepository;
import com.ua.itclusterjava2024.service.interfaces.SpecialistService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialistServiceImpl implements SpecialistService {
    private final SpecialistRepository specialistRepository;

    public SpecialistServiceImpl(SpecialistRepository specialistRepository) {
        this.specialistRepository = specialistRepository;
    }

    @Override
    public Specialist create(Specialist specialty) {
        return specialistRepository.save(specialty);
    }

    @Override
    public Optional<Specialist> readById(long id) {
        return specialistRepository.findById(id);
    }

    @Override
    public Specialist update(long id, Specialist specialist) {
        specialist.setId(id);
        return specialistRepository.save(specialist);
    }

    @Override
    public void delete(long id) {
        specialistRepository.deleteById(id);
    }

    @Override
    public List<Specialist> getAll() {
        return specialistRepository.findAll();
    }

    @Override
    public Page<Specialist> getAll(Pageable pageable) {
        return specialistRepository.findAll(pageable);
    }


    @Override
    public void setVerified(Long specialistId, Boolean verified) {
        if (specialistRepository.findById(specialistId).isPresent()) {
            Specialist specialist = specialistRepository.findById(specialistId).get();
            if (!specialist.getVerified().equals(verified)) {
                specialist.setVerified(verified);
                update(specialist.getId(), specialist);
            }
        }
    }


}

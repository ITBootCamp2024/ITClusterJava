package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.Role;
import com.ua.itclusterjava2024.entity.Specialist;
import com.ua.itclusterjava2024.entity.Teachers;
import com.ua.itclusterjava2024.repository.RoleRepository;
import com.ua.itclusterjava2024.repository.TeachersRepository;
import com.ua.itclusterjava2024.service.interfaces.TeachersService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeachersServiceImpl implements TeachersService {

    private final TeachersRepository teachersRepository;
    private final RoleRepository roleRepository;

    public TeachersServiceImpl(TeachersRepository teachersRepository, RoleRepository roleRepository) {
        this.teachersRepository = teachersRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Teachers create(Teachers teacher) {
        Role teacherRole = roleRepository.findByName("teacher")
                .orElseThrow(() -> new EntityNotFoundException("Role 'teacher' not found"));
        teacher.setRole(teacherRole);
        return teachersRepository.save(teacher);
    }

    @Override
    public Optional<Teachers> readById(long id) {
        return teachersRepository.findById(id);
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

    @Override
    public void setVerified(Long teacher_id, Boolean verified) {
        if (teachersRepository.findById(teacher_id).isPresent()) {
            Teachers teacher = teachersRepository.findById(teacher_id).get();
            if (!teacher.getVerified().equals(verified)) {
                teacher.setVerified(verified);
                update(teacher.getId(), teacher);
            }
        }
    }
}

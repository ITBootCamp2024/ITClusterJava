package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.Department;
import com.ua.itclusterjava2024.repository.DepartmentRepository;
import com.ua.itclusterjava2024.service.interfaces.DepartmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department create(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Optional<Department> readById(long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Department update(long id, Department department) {
        department.setId(id);
        return departmentRepository.save(department);
    }

    @Override
    public void delete(long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Page<Department> getAll(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }
}

package com.ua.itclusterjava2024.repository;

import com.ua.itclusterjava2024.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}

package com.ua.itclusterjava2024.repositories;

import com.ua.itclusterjava2024.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer>{
}

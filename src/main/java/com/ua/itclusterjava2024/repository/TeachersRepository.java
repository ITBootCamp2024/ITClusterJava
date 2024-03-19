package com.ua.itclusterjava2024.repository;

import com.ua.itclusterjava2024.entity.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeachersRepository extends JpaRepository<Teachers, Long>{
}

package com.ua.itclusterjava2024.repository;

import com.ua.itclusterjava2024.dto.SpecialistDTO;
import com.ua.itclusterjava2024.entity.Specialist;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialistRepository extends JpaRepository<Specialist, Long> {
}
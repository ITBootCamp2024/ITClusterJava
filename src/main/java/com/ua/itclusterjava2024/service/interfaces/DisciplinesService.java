package com.ua.itclusterjava2024.service.interfaces;

import com.ua.itclusterjava2024.dto.DisciplinesDTO;
import com.ua.itclusterjava2024.entity.Disciplines;

import java.util.List;

public interface DisciplinesService extends Service<Disciplines>{
    List<Disciplines> findByTeacherId(Long teacherId);
}

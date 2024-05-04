package com.ua.itclusterjava2024.service.interfaces;

import com.ua.itclusterjava2024.dto.DisciplinesDTO;
import com.ua.itclusterjava2024.entity.Disciplines;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DisciplinesService extends Service<Disciplines>{
    List<Disciplines> findByTeacherId(Long teacherId);
    List<Disciplines> findByDisciplineGroupId(Long id);
}

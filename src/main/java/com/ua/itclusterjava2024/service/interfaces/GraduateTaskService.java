package com.ua.itclusterjava2024.service.interfaces;

import com.ua.itclusterjava2024.entity.GraduateTask;

import java.util.List;

public interface GraduateTaskService extends Service<GraduateTask> {
    List<GraduateTask> findAllBySyllabusId(Long syllabusId);
}

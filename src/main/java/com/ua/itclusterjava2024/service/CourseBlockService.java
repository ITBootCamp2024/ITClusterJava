package com.ua.itclusterjava2024.service;

import com.ua.itclusterjava2024.entity.CourseBlock;

import java.util.List;

public interface CourseBlockService {
    List<CourseBlock> findAll();
    CourseBlock saveCourseBlock(CourseBlock patient);
    CourseBlock updateCourseBlock(Long id, CourseBlock patient);
    void deleteCourseBlockById(Long id);
}

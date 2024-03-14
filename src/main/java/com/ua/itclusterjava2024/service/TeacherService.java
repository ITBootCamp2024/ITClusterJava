package com.ua.itclusterjava2024.service;

import com.ua.itclusterjava2024.dao.EntityDAO.TeacherDAO;
import com.ua.itclusterjava2024.entity.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherDAO teacherDAO;

    public TeacherService(TeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }
    public List<Teacher> findAll(){
        List<Teacher> teachers = teacherDAO.findAll();
        return teachers;
    }
}

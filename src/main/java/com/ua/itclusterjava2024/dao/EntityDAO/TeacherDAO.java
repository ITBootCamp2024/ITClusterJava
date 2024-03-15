package com.ua.itclusterjava2024.dao.EntityDAO;

import com.ua.itclusterjava2024.entity.Teacher;

import java.util.List;

public interface TeacherDAO {
    Teacher add(Teacher teacher);
    Teacher update(Teacher teacher);
    List<Teacher> findAll();
    void delete(long id);
    Teacher findById(long id);
}

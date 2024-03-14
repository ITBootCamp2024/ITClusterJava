package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.entity.Teacher;
import com.ua.itclusterjava2024.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }
    @GetMapping(value = "/getTeachers")
    public List<Teacher> getAll() {
        return teacherService.findAll();
    }
}

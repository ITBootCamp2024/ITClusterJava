package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.entity.Teacher;
import com.ua.itclusterjava2024.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping(value = "/getTeachers")
    public ResponseEntity<List<Teacher>> getAll() {
        List<Teacher> teachers = teacherService.findAll();
        return ResponseEntity.ok(teachers);
    }

    @PutMapping(value = "/updateTeachers")
    public ResponseEntity<Teacher> update(@RequestBody Teacher teacher) {
        Teacher updatedTeacher = teacherService.update(teacher);
        if (updatedTeacher != null) {
            return ResponseEntity.ok(updatedTeacher);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping(value = "/deleteTeachers/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) {
        teacherService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/addTeacher")
    public ResponseEntity<Teacher> add(@RequestBody Teacher teacher) {
        Teacher createdTeacher = teacherService.add(teacher);
        if (createdTeacher != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTeacher);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}

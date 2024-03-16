package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.entity.Teacher;
import com.ua.itclusterjava2024.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService service;

    @Autowired
    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @GetMapping("/getPage")
    public Object hello() {
        Map<String, String> object = new HashMap<>();
        object.put("name", "ItCluster");
        object.put("hello, World", "!");
        return object;
    }

    @GetMapping
    public List<Teacher> getTeachers() {
        return service.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEntity(@PathVariable int id, @RequestBody Teacher updatedTeacher) {
        try {
            service.update(id, updatedTeacher);
            return ResponseEntity.ok("Entity updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update entity: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Void> addTeacher(@RequestBody Teacher newTeacher) {
        service.add(newTeacher);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}



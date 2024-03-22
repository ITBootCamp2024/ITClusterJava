package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.entity.CourseBlock;
import com.ua.itclusterjava2024.entity.Teachers;
import com.ua.itclusterjava2024.entity.University;
import com.ua.itclusterjava2024.service.implementation.TeachersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teachers")
public class TeachersController {

    private final TeachersServiceImpl service;

    @Autowired
    public TeachersController(TeachersServiceImpl service) {
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
    public List<Teachers> getTeachers() {
        return service.getAll();
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<String> updateEntity(@RequestBody Teachers updatedTeachers) {
//        try {
//            service.update(updatedTeachers);
//            return ResponseEntity.ok("Entity updated successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update entity: " + e.getMessage());
//        }
//    }

    @PutMapping("/{id}")
    public List<Teachers> updateEntity(@PathVariable("id") Long id,
                                       @RequestBody Teachers updatedTeachers) {
            service.update(id, updatedTeachers);
            return service.getAll();
    }

    @DeleteMapping("/{id}")
    public List<Teachers> delete(@PathVariable long id) {
        service.delete(id);
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Teachers findById(@PathVariable long id) {
        return service.readById(id);
    }

    @PostMapping
    public List<Teachers> saveCourseBlock(@RequestBody Teachers newTeachers){
        service.create(newTeachers);
        return service.getAll();
    }
}



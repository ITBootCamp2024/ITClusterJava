package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.entity.Specialist;
import com.ua.itclusterjava2024.entity.Teachers;
import com.ua.itclusterjava2024.repository.TeachersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teachers")
public class TeachersListController {
    private final TeachersRepository teachersRepository;

    public TeachersListController(TeachersRepository teachersRepository) {
        this.teachersRepository = teachersRepository;
    }

    @GetMapping("/verified")
    public ResponseEntity<?> getVerifiedTeachersList(){
        List<Teachers> teachersList = teachersRepository.findAll();
        long verifiedCount = teachersList.stream().filter(Teachers::getVerified).count();
        long notVerifiedCount = teachersList.size() - verifiedCount;

        Map<String, Object> response = new HashMap<>();
        response.put("teachers", teachersList);
        response.put("verified_count", verifiedCount);
        response.put("not_verified_count", notVerifiedCount);

        return ResponseEntity.ok(response);
    }
}

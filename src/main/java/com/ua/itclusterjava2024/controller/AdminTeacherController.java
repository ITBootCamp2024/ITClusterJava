package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.*;
import com.ua.itclusterjava2024.dto.request.TeacherVerifiedRequest;
import com.ua.itclusterjava2024.entity.Teachers;
import com.ua.itclusterjava2024.service.interfaces.TeachersService;
import com.ua.itclusterjava2024.wrappers.TeacherPageWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher/verified")
public class AdminTeacherController {

    private final TeachersService teachersService;

    public AdminTeacherController(TeachersService teachersService) {
        this.teachersService = teachersService;
    }


    @GetMapping()
    public ResponseEntity<TeacherPageWrapper> getVerifiedTeachersList(){
        List<TeachersDTO> teachersList = teachersService.getAll().stream().map(this::convertToDTO).toList();
        long verifiedCount = teachersList.size();
        long notVerifiedCount = teachersList.size() - verifiedCount;

        TeacherPageWrapper response = new TeacherPageWrapper(new TeacherPageWrapper.Content(teachersList), verifiedCount, notVerifiedCount);

        return ResponseEntity.ok(response);
    }

    @PatchMapping()
    public ResponseEntity<TeacherPageWrapper> updateVerifiedTeachersList(@RequestBody TeacherVerifiedRequest request){
        teachersService.setVerified(request.getTeacherId(), request.getVerified());
        return getVerifiedTeachersList();
    }

    public TeachersDTO convertToDTO(Teachers teacher) {
        return TeachersDTO.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .email(teacher.getEmail())
                .verified(teacher.getVerified())
                .position(PositionDTO.builder()
                        .id(teacher.getPosition().getId())
                        .name(teacher.getPosition().getName())
                        .build())
                .department(DepartmentDTO.builder()
                        .id(teacher.getDepartment().getId())
                        .name(teacher.getDepartment().getName())
                        .build())
                .university(UniversityDTO.builder()
                        .id(teacher.getDepartment().getUniversity().getId())
                        .name(teacher.getDepartment().getUniversity().getName())
                        .build()).build();
    }
}

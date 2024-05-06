package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.*;
import com.ua.itclusterjava2024.entity.Teachers;
import com.ua.itclusterjava2024.service.interfaces.TeachersService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher/verified")
public class TeachersListController {

    private final TeachersService teachersService;
    private final ModelMapper modelMapper;


    @GetMapping()
    public ResponseEntity<Map<String, Object>> getVerifiedTeachersList(){
        return getTeachersList();
    }

    //TODO
    @PatchMapping("/{admin_id}")
    public ResponseEntity<?> updateVerifiedTeachersList(@PathVariable Long admin_id){
        Teachers teacher = teachersService.readById(admin_id)
                .orElseThrow(() -> new NotFoundException("Teacher's not found with id: " + admin_id));
  
        teacher.setVerified(!teacher.getVerified());
        teachersService.update(admin_id, teacher);
        return getTeachersList();
    }

    private ResponseEntity<Map<String, Object>> getTeachersList() {
        List<TeachersDTO> teachersList = teachersService.getAll().stream().map(this::convertToDTO).toList();
        long verifiedCount = teachersList.stream().filter(TeachersDTO::getVerified).count();
        long notVerifiedCount = teachersList.size() - verifiedCount;

        Map<String, Object> response = new HashMap<>();
        response.put("teachers", teachersList);
        response.put("verified_count", verifiedCount);
        response.put("not_verified_count", notVerifiedCount);

        return ResponseEntity.ok(response);
    }

    public TeachersDTO convertToDTO(Teachers teacher) {
        TeachersDTO dto = modelMapper.map(teacher, TeachersDTO.class);
        dto.setPosition(PositionDTO.builder().id(teacher.getPosition().getId()).name(teacher.getPosition().getName()).build());
        dto.setDepartment(DepartmentDTO.builder()
                .id(teacher.getDepartment().getId())
                .name(teacher.getDepartment().getName())
                .build());
        dto.setUniversity(UniversityDTO.builder()
                .id(teacher.getDepartment().getUniversity().getId())
                .name(teacher.getDepartment().getUniversity().getName())
                .build());
        dto.setRole(RoleDTO.builder()
                .id(teacher.getRole().getId())
                .name(teacher.getRole().getName())
                .build());
        return dto;
    }
}

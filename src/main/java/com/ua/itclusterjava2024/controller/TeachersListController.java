package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.*;
import com.ua.itclusterjava2024.entity.Teachers;
import com.ua.itclusterjava2024.exceptions.NotFoundException;
import com.ua.itclusterjava2024.service.interfaces.TeachersService;
import com.ua.itclusterjava2024.wrappers.Patcher;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teachers-verified")
public class TeachersListController {
    private final TeachersService teachersService;
    private final Patcher<Teachers> patcher;
    private final ModelMapper modelMapper;

    public TeachersListController(TeachersService teachersService, Patcher<Teachers> patcher, ModelMapper modelMapper) {
        this.teachersService = teachersService;
        this.patcher = patcher;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public ResponseEntity<?> getVerifiedTeachersList(){
        return getTeachersList();
    }

    //TODO
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateVerifiedTeachersList(@PathVariable Long id){
        Teachers teacher = teachersService.readById(id)
                .orElseThrow(() -> new NotFoundException("Teacher's not found with id: " + id));

        teacher.setVerified(!teacher.getVerified());
        teachersService.update(id, teacher);
        return getTeachersList();
    }

    private ResponseEntity<?> getTeachersList() {
        List<TeachersDTO> teachersList = teachersService.getAll().stream().map(this::convertToDTO).toList();;
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

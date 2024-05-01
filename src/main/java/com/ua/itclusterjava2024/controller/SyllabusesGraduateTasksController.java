package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.GraduateTaskDTO;
import com.ua.itclusterjava2024.entity.GraduateTask;
import com.ua.itclusterjava2024.service.interfaces.GraduateTaskService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/syllabuses/graduate-tasks")
public class SyllabusesGraduateTasksController {

    private final GraduateTaskService graduateTaskService;
    private final ModelMapper modelMapper;

    @GetMapping("/{syllabus_id}")
    public ResponseEntity<Map<String, Object>> getGraduateTasksBySyllabusId(@PathVariable("syllabus_id") Long syllabusId) {
        List<GraduateTaskDTO> graduateTasksDTO = graduateTaskService.findAllBySyllabusId(syllabusId)
                .stream().map(this::convertToDTO)
                .toList();

        Map<String, Object> response = Map.of(
                "content", Map.of(
                        "graduate_tasks", graduateTasksDTO,
                        "syllabus_id", syllabusId
                )
        );
        return ResponseEntity.ok(response);
    }



    private GraduateTaskDTO convertToDTO(GraduateTask graduateTask) {
        modelMapper.typeMap(GraduateTask.class, GraduateTaskDTO.class)
                .addMappings(mapper -> mapper.skip(GraduateTaskDTO::setSyllabusId));
        return modelMapper.map(graduateTask, GraduateTaskDTO.class);
    }

    private GraduateTask convertToEntity(GraduateTaskDTO graduateTaskDTO) {
        return modelMapper.map(graduateTaskDTO, GraduateTask.class);
    }
}

package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.*;
import com.ua.itclusterjava2024.entity.BaseInformationSyllabus;
import com.ua.itclusterjava2024.service.interfaces.SyllabusesBaseInfoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/syllabuses/base-info")
@RequiredArgsConstructor
public class SyllabusesBaseInfoController {

    private final SyllabusesBaseInfoService baseInfoService;
    private final ModelMapper modelMapper;


    @GetMapping("/{syllabus_id}")
    public ResponseEntity<Map<String, Object>> getBaseInfoBySyllabusId(@PathVariable("syllabus_id") Long syllabusId) {

        BaseInformationSyllabusDTO baseInfoSyllabusDTO = convertToDTO(baseInfoService.getBaseInfoBySyllabus(syllabusId).orElse(null));

        Map<String, Object> specialty = new HashMap<>();
        specialty.put("id", baseInfoSyllabusDTO.getSpecialty().getId());
        specialty.put("code", baseInfoSyllabusDTO.getSpecialty().getCode());
        specialty.put("name", baseInfoSyllabusDTO.getSpecialty().getName());

        Map<String, Object> educationProgram = new HashMap<>();
        educationProgram.put("id", baseInfoSyllabusDTO.getSyllabus().getDisciplines().getEducationPrograms().getId());
        educationProgram.put("name", baseInfoSyllabusDTO.getSyllabus().getDisciplines().getEducationPrograms().getName());

        Map<String, Object> disciplineBlock = new HashMap<>();
        disciplineBlock.put("id", baseInfoSyllabusDTO.getSyllabus().getDisciplines().getDisciplineGroups().getBlock().getId());
        disciplineBlock.put("name", baseInfoSyllabusDTO.getSyllabus().getDisciplines().getDisciplineGroups().getBlock().getName());

        Map<String, Object> discipline = new HashMap<>();
        discipline.put("id", baseInfoSyllabusDTO.getSyllabus().getDisciplines().getId());
        discipline.put("name", baseInfoSyllabusDTO.getSyllabus().getDisciplines().getName());

        Map<String, Object> baseInfo = new HashMap<>();
        baseInfo.put("specialty", specialty);
        baseInfo.put("education_program", educationProgram);
        baseInfo.put("discipline_block", disciplineBlock);
        baseInfo.put("discipline", discipline);
        baseInfo.put("student_count", baseInfoSyllabusDTO.getStudentCount());
        baseInfo.put("course", baseInfoSyllabusDTO.getCourse());
        baseInfo.put("semester", baseInfoSyllabusDTO.getSemester());

        Map<String, Object> content = new HashMap<>();
        content.put("base_info", baseInfo);
        content.put("syllabus_id", syllabusId);

        Map<String, Object> response = new HashMap<>();
        response.put("content", content);

        return ResponseEntity.ok(response);
    }

    // TODO
    @PatchMapping("/{syllabus_id}")
    public ResponseEntity<Map<String, Object>> updateBaseInfoBySyllabusId(@PathVariable("syllabus_id") Long syllabusId){

        BaseInformationSyllabusDTO baseInfoSyllabusDTO = convertToDTO(baseInfoService.getBaseInfoBySyllabus(syllabusId).orElse(null));
        Map<String, Object> response = new HashMap<>();
        response.put("content", baseInfoSyllabusDTO);
        return ResponseEntity.ok(response);
    }

    private BaseInformationSyllabusDTO convertToDTO(BaseInformationSyllabus baseInfo) {
        BaseInformationSyllabusDTO baseInfoSyllabusDTO = modelMapper.map(baseInfo, BaseInformationSyllabusDTO.class);
        baseInfoSyllabusDTO.setSpecialty(SpecialtyDTO.builder()
                .id(baseInfoSyllabusDTO.getSpecialty().getId())
                .code(baseInfoSyllabusDTO.getSpecialty().getCode())
                .name(baseInfoSyllabusDTO.getSpecialty().getName())
                .build());

        baseInfoSyllabusDTO.setSyllabus(SyllabusesDTO.builder()
                .id(baseInfoSyllabusDTO.getSyllabus().getId())
                .name(baseInfoSyllabusDTO.getSyllabus().getName())
                .status(baseInfoSyllabusDTO.getSyllabus().getStatus())
                .disciplines(baseInfoSyllabusDTO.getSyllabus().getDisciplines())
                .build());

        return baseInfoSyllabusDTO;
    }
}

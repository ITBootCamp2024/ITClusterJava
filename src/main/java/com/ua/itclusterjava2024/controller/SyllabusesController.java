package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.DisciplinesDTO;
import com.ua.itclusterjava2024.dto.SyllabusesDTO;
import com.ua.itclusterjava2024.entity.Disciplines;
import com.ua.itclusterjava2024.entity.Syllabuses;
import com.ua.itclusterjava2024.service.interfaces.SyllabusesService;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import com.ua.itclusterjava2024.wrappers.Patcher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/syllabuses")
public class SyllabusesController {

    private final SyllabusesService syllabusesService;
    private final ModelMapper modelMapper;
    private final EntityManager entityManager;
    private final Patcher<Syllabuses> patcher;


    @GetMapping()
    public PageWrapper<SyllabusesDTO> findAll() {
        List<SyllabusesDTO> syllabusesPage = syllabusesService.getAll().stream()
                .map(this::convertToDTO)
                .toList();

        PageWrapper<SyllabusesDTO> pageWrapper = new PageWrapper<>();
        pageWrapper.setContent(syllabusesPage);
        pageWrapper.setTotalElements(syllabusesPage.size());
        return pageWrapper;
    }

    @PostMapping
    public PageWrapper<SyllabusesDTO> save(@RequestBody SyllabusesDTO syllabusesDTO) {
        syllabusesService.create(convertToEntity(syllabusesDTO));
        entityManager.clear();
        return findAll();
    }

    @PatchMapping("/{id}")
    public PageWrapper<SyllabusesDTO> update(@PathVariable("id") Long id,
                                             @RequestBody SyllabusesDTO syllabusesDTO) {
        Syllabuses existingSyllabus = syllabusesService.readById(id).
                orElseThrow(() -> new EntityNotFoundException("Syllabuses not found with id: " + id));
        Syllabuses updatedSyllabus = convertToEntity(syllabusesDTO);
        try {
            patcher.patch(existingSyllabus, updatedSyllabus);
            syllabusesService.update(id, existingSyllabus);
        } catch (Exception e) {
            e.printStackTrace();
        }
        entityManager.clear();
        return findAll();
    }

    @GetMapping("/{id}")
    public SyllabusesDTO findById(@PathVariable Long id) {
        return convertToDTO(syllabusesService.readById(id).orElse(null));
    }

    @DeleteMapping("/{id}")
    public PageWrapper<SyllabusesDTO> delete(@PathVariable Long id) {
        syllabusesService.delete(id);
        return findAll();
    }

    private Syllabuses convertToEntity(SyllabusesDTO syllabusesDTO) {
        Syllabuses syllabuses = modelMapper.map(syllabusesDTO, Syllabuses.class);
        if(syllabuses.getDisciplines() != null){
            syllabuses.setDisciplines(modelMapper.map(syllabusesDTO.getDisciplines(), Disciplines.class));
        }

        return syllabuses;
    }

    private SyllabusesDTO convertToDTO(Syllabuses syllabuses) {
        SyllabusesDTO syllabusesDTO = modelMapper.map(syllabuses, SyllabusesDTO.class);
        syllabusesDTO.setDisciplines(DisciplinesDTO.builder()
                .id(syllabusesDTO.getDisciplines().getId())
                .name(syllabusesDTO.getDisciplines().getName())
                .build());
        return syllabusesDTO;
    }
}

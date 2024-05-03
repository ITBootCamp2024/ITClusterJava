package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.SyllabusDTO;
import com.ua.itclusterjava2024.entity.Syllabuses;
import com.ua.itclusterjava2024.service.interfaces.SyllabusesService;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/syllabuses")
public class SyllabusesSpecialistController {

    private final SyllabusesService syllabusesService;
    private final ModelMapper modelMapper;

    @GetMapping("/proposed/{specialist_id}")
    public PageWrapper<SyllabusDTO> getProposedSyllabuses(@PathVariable("specialist_id") Long specialistId) {
        List<Syllabuses> proposedSyllabuses = syllabusesService.findSyllabusesBySpecialistId(specialistId, false);

        List<SyllabusDTO> syllabusDTOS = proposedSyllabuses.stream()
                        .map(this::mapToProposedSyllabusDTO)
                        .toList();

        return new PageWrapper<>(syllabusDTOS, proposedSyllabuses.size());
    }

    private SyllabusDTO mapToProposedSyllabusDTO(Syllabuses syllabus) {
        SyllabusDTO syllabusDTO = new SyllabusDTO();
        syllabusDTO.setSyllabusId(syllabus.getId());
        syllabusDTO.setDiscipline(syllabus.getDisciplines().getName());
        syllabusDTO.setDisciplineBlock(syllabus.getDisciplines().getDiscipline_group().getBlock_id().getName());
        syllabusDTO.setAccepted(false);
        return syllabusDTO;
    }

    private SyllabusDTO mapToRefeedSyllabusDTO(Syllabuses syllabus) {
        SyllabusDTO syllabusDTO = new SyllabusDTO();
        syllabusDTO.setSyllabusId(syllabus.getId());
        syllabusDTO.setDiscipline(syllabus.getDisciplines().getName());
        syllabusDTO.setStatus(syllabus.getStatus());
        syllabusDTO.setDisciplineBlock(syllabus.getDisciplines().getDiscipline_group().getBlock_id().getName());
        syllabusDTO.setAccepted(true);
        return syllabusDTO;
    }

    @GetMapping("/refereed/{specialist_id}")
    public PageWrapper<SyllabusDTO> getRefereedSyllabuses(@PathVariable("specialist_id") Long specialistId) {
        List<Syllabuses> proposedSyllabuses = syllabusesService.findSyllabusesBySpecialistId(specialistId, true);

        List<SyllabusDTO> syllabusDTOS = proposedSyllabuses.stream()
                .map(this::mapToRefeedSyllabusDTO)
                .toList();

        return new PageWrapper<>(syllabusDTOS, proposedSyllabuses.size());
    }

}

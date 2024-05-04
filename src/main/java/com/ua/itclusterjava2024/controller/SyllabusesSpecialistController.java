package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.SyllabusSpecialistDTO;
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
    public PageWrapper<SyllabusSpecialistDTO> getProposedSyllabuses(@PathVariable("specialist_id") Long specialistId) {
        List<Syllabuses> proposedSyllabuses = syllabusesService.findSyllabusesBySpecialistId(specialistId, false);

        List<SyllabusSpecialistDTO> syllabusDTOS = proposedSyllabuses.stream()
                .map(syllabus -> mapToSyllabusSpecialistDTO(syllabus, false))
                .toList();

        return new PageWrapper<>(syllabusDTOS, proposedSyllabuses.size());
    }

    @GetMapping("/refereed/{specialist_id}")
    public PageWrapper<SyllabusSpecialistDTO> getRefereedSyllabuses(@PathVariable("specialist_id") Long specialistId) {
        List<Syllabuses> proposedSyllabuses = syllabusesService.findSyllabusesBySpecialistId(specialistId, true);

        List<SyllabusSpecialistDTO> syllabusDTOS = proposedSyllabuses.stream()
                .map(syllabuses -> mapToSyllabusSpecialistDTO(syllabuses, true))
                .toList();

        return new PageWrapper<>(syllabusDTOS, proposedSyllabuses.size());
    }


    private SyllabusSpecialistDTO mapToSyllabusSpecialistDTO(Syllabuses syllabus, Boolean accepted) {
        SyllabusSpecialistDTO syllabusDTO = new SyllabusSpecialistDTO();
        syllabusDTO.setSyllabusId(syllabus.getId());
        syllabusDTO.setDiscipline(syllabus.getDisciplines().getName());
        syllabusDTO.setDisciplineBlock(syllabus.getDisciplines().getDisciplineGroups().getDisciplineBlocks().getName());
        syllabusDTO.setAccepted(accepted);
        return syllabusDTO;
    }

}

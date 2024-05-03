package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.ProposedSyllabusDTO;
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
    public PageWrapper<ProposedSyllabusDTO> getProposedSyllabuses(@PathVariable("specialist_id") Long specialistId) {
        List<Syllabuses> proposedSyllabuses = syllabusesService.findNotAcceptedSyllabusesBySpecialistId(specialistId);

        List<ProposedSyllabusDTO> proposedSyllabusDTOS = proposedSyllabuses.stream()
                        .map(this::mapToProposedSyllabusDTO)
                        .toList();

        return new PageWrapper<>(proposedSyllabusDTOS, proposedSyllabuses.size());
    }

    private ProposedSyllabusDTO mapToProposedSyllabusDTO(Syllabuses syllabus) {
        ProposedSyllabusDTO proposedSyllabusDTO = new ProposedSyllabusDTO();
        proposedSyllabusDTO.setSyllabusId(syllabus.getId());
        proposedSyllabusDTO.setDiscipline(syllabus.getDisciplines().getName());
        proposedSyllabusDTO.setDisciplineBlock(syllabus.getDisciplines().getDiscipline_group().getBlock_id().getName());
        proposedSyllabusDTO.setAccepted(false);
        return proposedSyllabusDTO;
    }


}

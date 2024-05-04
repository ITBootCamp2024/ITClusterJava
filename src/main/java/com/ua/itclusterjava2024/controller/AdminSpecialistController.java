package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.DisciplineGroupsDTO;
import com.ua.itclusterjava2024.dto.SpecialistDTO;
import com.ua.itclusterjava2024.entity.DisciplineBlocks;
import com.ua.itclusterjava2024.entity.DisciplineGroups;
import com.ua.itclusterjava2024.entity.Specialist;
import com.ua.itclusterjava2024.service.interfaces.ReviewsService;
import com.ua.itclusterjava2024.service.interfaces.SpecialistService;
import com.ua.itclusterjava2024.service.interfaces.SyllabusesService;
import com.ua.itclusterjava2024.wrappers.SpecialistPageWrapper;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/specialist/verified")
public class AdminSpecialistController {
    final SpecialistService specialistService;
    final SyllabusesService syllabusesService;
    final ReviewsService reviewsService;
    final ModelMapper modelMapper;

    public AdminSpecialistController(SpecialistService specialistService, SyllabusesService syllabusesService, ReviewsService reviewsService, ModelMapper modelMapper) {
        this.specialistService = specialistService;
        this.syllabusesService = syllabusesService;
        this.reviewsService = reviewsService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/{admin_id}")
    public ResponseEntity<SpecialistPageWrapper> getVerifiedSpecialists(@PathVariable("admin_id") Long adminId) {
        List<SpecialistDTO> allSpecialistsDTO = specialistService.getAll().stream()
                .map(this::convertToDTO)
                .toList();

        long verifiedCount = allSpecialistsDTO.size();
        long notVerifiedCount = allSpecialistsDTO.size() - verifiedCount;
        SpecialistPageWrapper response = new SpecialistPageWrapper(
                new SpecialistPageWrapper.Content(adminId, allSpecialistsDTO),
                verifiedCount,
                notVerifiedCount
        );

        return ResponseEntity.ok(response);
    }

    private DisciplineGroups convertToEntity(DisciplineGroupsDTO disciplineGroupsDTO) {
        DisciplineGroups disciplineGroups = modelMapper.map(disciplineGroupsDTO, DisciplineGroups.class);
        if (disciplineGroupsDTO.getBlock() != null) {
            disciplineGroups.setDisciplineBlocks(modelMapper.map(disciplineGroupsDTO.getBlock(), DisciplineBlocks.class));
        }
        return disciplineGroups;
    }

    private SpecialistDTO convertToDTO(Specialist specialist) {
        return SpecialistDTO.builder()
                .id(specialist.getId())
                .name(specialist.getName())
                .position(specialist.getPosition())
                .company(specialist.getCompany())
                .email(specialist.getEmail())
                .verified(specialist.getVerified())
                .allSyllabuses(syllabusesService.findSyllabusesBySpecialistId(specialist.getId(), false).stream().count())
                .syllabusesForReview(syllabusesService.findSyllabusesBySpecialistId(specialist.getId(), true).stream().count())
                .build();
    }
}

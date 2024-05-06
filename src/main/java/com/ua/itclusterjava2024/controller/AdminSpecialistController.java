package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.DisciplineGroupsDTO;
import com.ua.itclusterjava2024.dto.SpecialistDTO;
import com.ua.itclusterjava2024.dto.request.UpdateVerifiedRequest;
import com.ua.itclusterjava2024.entity.DisciplineBlocks;
import com.ua.itclusterjava2024.entity.DisciplineGroups;
import com.ua.itclusterjava2024.entity.Specialist;
import com.ua.itclusterjava2024.entity.Syllabuses;
import com.ua.itclusterjava2024.service.interfaces.ReviewsService;
import com.ua.itclusterjava2024.service.interfaces.SpecialistService;
import com.ua.itclusterjava2024.service.interfaces.SyllabusesService;
import com.ua.itclusterjava2024.wrappers.SpecialistPageWrapper;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/specialist/verified")
public class AdminSpecialistController {
    final SpecialistService specialistService;
    final ModelMapper modelMapper;

    public AdminSpecialistController(SpecialistService specialistService, ModelMapper modelMapper) {
        this.specialistService = specialistService;
        this.modelMapper = modelMapper;
    }


    @GetMapping
    public ResponseEntity<SpecialistPageWrapper> find() {
        List<SpecialistDTO> allSpecialistsDTO = specialistService.getAll().stream()
                .map(this::convertToDTO)
                .toList();

        long verifiedCount = allSpecialistsDTO.size();
        long notVerifiedCount = allSpecialistsDTO.size() - verifiedCount;
        SpecialistPageWrapper response = new SpecialistPageWrapper(
                new SpecialistPageWrapper.Content(allSpecialistsDTO),
                verifiedCount,
                notVerifiedCount
        );

        return ResponseEntity.ok(response);
    }

    @PatchMapping
    public ResponseEntity<SpecialistPageWrapper> update(@RequestBody UpdateVerifiedRequest request) {
        specialistService.setVerified(request.getSpecialist_id(), request.getVerified());
        return find();
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
                .allSyllabuses(specialist.getAllSyllabuses())
                .syllabusesForReview(specialist.getSyllabusesForReview())
                .build();
    }
}

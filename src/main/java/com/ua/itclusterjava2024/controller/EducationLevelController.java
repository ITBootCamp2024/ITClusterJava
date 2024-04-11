package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.EducationLevelDTO;
import com.ua.itclusterjava2024.entity.EducationLevel;
import com.ua.itclusterjava2024.exceptions.NotFoundException;
import com.ua.itclusterjava2024.service.interfaces.EducationLevelsService;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import com.ua.itclusterjava2024.wrappers.Patcher;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/education-levels")
public class EducationLevelController {

    private final EducationLevelsService educationLevelsService;
    private final ModelMapper modelMapper;
    private final Patcher<EducationLevel> patcher;

    @Autowired
    public EducationLevelController(EducationLevelsService educationLevelsService, ModelMapper modelMapper, Patcher<EducationLevel> patcher) {
        this.educationLevelsService = educationLevelsService;
        this.modelMapper = modelMapper;
        this.patcher = patcher;
    }

    @GetMapping
    public PageWrapper<EducationLevelDTO> findAll() {
        List<EducationLevelDTO> levelsPage = educationLevelsService.getAll().stream()
                .map(this::convertToDTO).toList();

        PageWrapper<EducationLevelDTO> pageWrapper = new PageWrapper<>();
        pageWrapper.setContent(levelsPage);
        pageWrapper.setTotalElements(levelsPage.size());

        return pageWrapper;
    }

    @GetMapping("/{id}")
    public EducationLevelDTO findById(@PathVariable long id) {
        return convertToDTO(educationLevelsService.readById(id).orElse(null));
    }

    @PostMapping
    public PageWrapper<EducationLevelDTO> save(@RequestBody EducationLevelDTO educationLevelDTO) {
        educationLevelsService.create(convertToEntity(educationLevelDTO));
        return findAll();
    }

    @PatchMapping("/{id}")
    public PageWrapper<EducationLevelDTO> update(@PathVariable("id") Long id,
                                                 @RequestBody EducationLevel levels) {
        EducationLevel level = educationLevelsService.readById(id)
                .orElseThrow(() -> new NotFoundException("Education level not found"));
        try {
            patcher.patch(level, levels);
            educationLevelsService.update(id, level);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return findAll();
    }

    @DeleteMapping("/{id}")
    public PageWrapper<EducationLevelDTO> delete(@PathVariable long id) {
        educationLevelsService.delete(id);
        return findAll();
    }

    private EducationLevel convertToEntity(EducationLevelDTO educationLevelDTO) {
        return modelMapper.map(educationLevelDTO, EducationLevel.class);
    }

    private EducationLevelDTO convertToDTO(EducationLevel educationLevel) {
        return modelMapper.map(educationLevel, EducationLevelDTO.class);
    }
}

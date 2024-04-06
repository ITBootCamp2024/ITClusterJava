package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.EducationLevelDTO;
import com.ua.itclusterjava2024.dto.TeachersDTO;
import com.ua.itclusterjava2024.entity.EducationLevel;
import com.ua.itclusterjava2024.exceptions.ValidationException;
import com.ua.itclusterjava2024.service.interfaces.EducationLevelsService;
import com.ua.itclusterjava2024.validators.ProgramsLevelValidator;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import com.ua.itclusterjava2024.wrappers.Patcher;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/education-levels")
public class EducationLevelController {

    private final EducationLevelsService educationLevelsService;
    private final ModelMapper modelMapper;
    private final ProgramsLevelValidator programsLevelValidator;

    @Autowired
    Patcher patcher;

    @Autowired
    public EducationLevelController(EducationLevelsService educationLevelsService, ModelMapper modelMapper, ProgramsLevelValidator programsLevelValidator) {
        this.educationLevelsService = educationLevelsService;
        this.modelMapper = modelMapper;
        this.programsLevelValidator = programsLevelValidator;
    }

    @GetMapping
    public PageWrapper<EducationLevelDTO> findAll(@RequestParam(defaultValue = "1") int page) {
        int pageSize = 20;
        PageRequest pageable = PageRequest.of(page - 1, pageSize);
        Page<EducationLevelDTO> levelsPage = educationLevelsService.getAll(pageable).map(this::convertToDTO);

        PageWrapper<EducationLevelDTO> pageWrapper = new PageWrapper<>();
        pageWrapper.setContent(levelsPage.getContent());
        pageWrapper.setPageNumber(levelsPage.getNumber());
        pageWrapper.setTotalElements(levelsPage.getTotalElements());

        return pageWrapper;
    }

    @GetMapping("/{id}")
    public EducationLevelDTO findById(@PathVariable long id) {
        return convertToDTO(educationLevelsService.readById(id).orElse(null));
    }

    @PostMapping
    public PageWrapper<EducationLevelDTO> save(@RequestBody EducationLevelDTO educationLevelDTO, BindingResult bindingResult) {
        educationLevelsService.create(convertToEntity(educationLevelDTO));
        return findAll(1);
    }

    @PatchMapping("/{id}")
    public PageWrapper<EducationLevelDTO> update(@PathVariable("id") Long id,
                                                 @RequestBody EducationLevel levels,
                                                 BindingResult bindingResult
    ) {
         EducationLevel level = educationLevelsService.readById(id).orElse(null);

        try {
            patcher.patch(level, levels);
            educationLevelsService.create(level);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return findAll(1);
    }

    @DeleteMapping("/{id}")
    public PageWrapper<EducationLevelDTO> delete(@PathVariable long id) {
        educationLevelsService.delete(id);
        return findAll(1);
    }

    private EducationLevel convertToEntity(EducationLevelDTO educationLevelDTO){
        return modelMapper.map(educationLevelDTO, EducationLevel.class);
    }

    private EducationLevelDTO convertToDTO(EducationLevel educationLevel){
        return modelMapper.map(educationLevel, EducationLevelDTO.class);
    }
}

package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.DisciplineBlocksDTO;
import com.ua.itclusterjava2024.dto.UniversityDTO;
import com.ua.itclusterjava2024.entity.DisciplineBlocks;
import com.ua.itclusterjava2024.entity.University;
import com.ua.itclusterjava2024.exceptions.NotFoundException;
import com.ua.itclusterjava2024.exceptions.ValidationException;
import com.ua.itclusterjava2024.service.interfaces.DisciplineBlocksService;
import com.ua.itclusterjava2024.validators.CourseBlockValidator;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import com.ua.itclusterjava2024.wrappers.Patcher;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/discipline-blocks")
public class DisciplineBlocksController {
    private final DisciplineBlocksService disciplineBlocksService;
    private final ModelMapper modelMapper;
    private final CourseBlockValidator courseBlockValidator;
    private final Patcher<DisciplineBlocks> patcher;

    public DisciplineBlocksController(DisciplineBlocksService disciplineBlocksService, ModelMapper modelMapper, CourseBlockValidator courseBlockValidator, Patcher<DisciplineBlocks> patcher) {
        this.disciplineBlocksService = disciplineBlocksService;
        this.modelMapper = modelMapper;
        this.courseBlockValidator = courseBlockValidator;
        this.patcher = patcher;
    }
    @GetMapping
    public PageWrapper<DisciplineBlocksDTO> findAll() {
        List<DisciplineBlocksDTO> disciplineBlocksPage = disciplineBlocksService.getAll().stream().map(this::convertToDTO).toList();

        PageWrapper<DisciplineBlocksDTO> pageWrapper = new PageWrapper<>();
        pageWrapper.setContent(disciplineBlocksPage);
        pageWrapper.setTotalElements(disciplineBlocksPage.size());
        return pageWrapper;
    }
    @CrossOrigin
    @PostMapping
    public PageWrapper<DisciplineBlocksDTO> save(@RequestBody @Valid DisciplineBlocksDTO disciplineBlocksDTO, BindingResult bindingResult) {
        courseBlockValidator.validate(disciplineBlocksDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        disciplineBlocksService.create(convertToEntity(disciplineBlocksDTO));
        return findAll();
    }
    @CrossOrigin
    @PatchMapping("/{id}")
    public PageWrapper<DisciplineBlocksDTO> update(@PathVariable Long id, @RequestBody DisciplineBlocks updatedDisciplineBlocks) {
        DisciplineBlocks existingDisciplineBlocks = disciplineBlocksService.readById(id)
                .orElseThrow(() -> new NotFoundException("DisciplineBlocks not found with id: " + id));
        try {
            patcher.patch(existingDisciplineBlocks, updatedDisciplineBlocks);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        disciplineBlocksService.update(id, existingDisciplineBlocks);
        return findAll();
    }

    @GetMapping("/{id}")
    public DisciplineBlocksDTO findById(@PathVariable Long id){
        return convertToDTO(disciplineBlocksService.readById(id).orElse(null));
    }

    @DeleteMapping("/{id}")
    public PageWrapper<DisciplineBlocksDTO> delete(@PathVariable Long id){
        disciplineBlocksService.delete(id);
        return findAll();
    }

    private DisciplineBlocks convertToEntity(DisciplineBlocksDTO disciplineBlocksDTO){
        return modelMapper.map(disciplineBlocksDTO, DisciplineBlocks.class);
    }

    private DisciplineBlocksDTO convertToDTO(DisciplineBlocks disciplineBlocks){
        return modelMapper.map(disciplineBlocks, DisciplineBlocksDTO.class);
    }
}

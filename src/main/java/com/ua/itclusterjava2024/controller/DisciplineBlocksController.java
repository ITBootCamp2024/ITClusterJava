package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.DisciplineBlocksDTO;
import com.ua.itclusterjava2024.entity.DisciplineBlocks;
import com.ua.itclusterjava2024.exceptions.ValidationException;
import com.ua.itclusterjava2024.service.interfaces.DisciplineBlocksService;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import com.ua.itclusterjava2024.wrappers.Patcher;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/discipline-blocks")
@Slf4j
public class DisciplineBlocksController {
    private final DisciplineBlocksService disciplineBlocksService;
    private final ModelMapper modelMapper;
    private final Patcher<DisciplineBlocks> patcher;

    @Autowired
    public DisciplineBlocksController(DisciplineBlocksService disciplineBlocksService, ModelMapper modelMapper, Patcher<DisciplineBlocks> patcher) {
        this.disciplineBlocksService = disciplineBlocksService;
        this.modelMapper = modelMapper;
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
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        disciplineBlocksService.create(convertToEntity(disciplineBlocksDTO));
        return findAll();
    }

    @CrossOrigin
    @PatchMapping("/{id}")
    public PageWrapper<DisciplineBlocksDTO> update(@PathVariable Long id, @RequestBody DisciplineBlocks updatedDisciplineBlocks) {
        DisciplineBlocks existingDisciplineBlocks = disciplineBlocksService.readById(id)
                .orElseThrow(() -> new EntityNotFoundException("DisciplineBlocks not found with id: " + id));
        try {
            patcher.patch(existingDisciplineBlocks, updatedDisciplineBlocks);
        } catch (IllegalAccessException e) {
            log.error("Failed to update DisciplineBlocks with id: {}", id, e);
        }

        disciplineBlocksService.update(id, existingDisciplineBlocks);
        return findAll();
    }

    @GetMapping("/{id}")
    public DisciplineBlocksDTO findById(@PathVariable Long id) {
        return convertToDTO(disciplineBlocksService.readById(id).orElse(null));
    }

    @DeleteMapping("/{id}")
    public PageWrapper<DisciplineBlocksDTO> delete(@PathVariable Long id) {
        disciplineBlocksService.delete(id);
        return findAll();
    }

    private DisciplineBlocks convertToEntity(DisciplineBlocksDTO disciplineBlocksDTO) {
        return modelMapper.map(disciplineBlocksDTO, DisciplineBlocks.class);
    }

    private DisciplineBlocksDTO convertToDTO(DisciplineBlocks disciplineBlocks) {
        return modelMapper.map(disciplineBlocks, DisciplineBlocksDTO.class);
    }
}

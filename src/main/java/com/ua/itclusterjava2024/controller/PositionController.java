package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.*;
import com.ua.itclusterjava2024.entity.*;
import com.ua.itclusterjava2024.exceptions.NotFoundException;
import com.ua.itclusterjava2024.exceptions.ValidationException;
import com.ua.itclusterjava2024.service.interfaces.PositionService;
import com.ua.itclusterjava2024.validators.PositionValidator;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import com.ua.itclusterjava2024.wrappers.Patcher;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/position")
public class PositionController {
    private final PositionService positionService;
    private final ModelMapper modelMapper;
    private final PositionValidator positionValidator;
    private final Patcher patcher;

    public PositionController(PositionService positionService, ModelMapper modelMapper, PositionValidator positionValidator, Patcher patcher) {
        this.positionService = positionService;
        this.modelMapper = modelMapper;
        this.positionValidator = positionValidator;
        this.patcher = patcher;
    }

    @GetMapping
    public PageWrapper<PositionDTO> findAll() {
        List<PositionDTO> positionPage = positionService.getAll().stream()
                .map(this::convertToDTO)
                .toList();

        PageWrapper<PositionDTO> pageWrapper = new PageWrapper<>();
        pageWrapper.setContent(positionPage);
        pageWrapper.setTotalElements(positionPage.size());
        return pageWrapper;
    }

    @PostMapping
    public PageWrapper<PositionDTO> save(@RequestBody @Valid PositionDTO positionDTO, BindingResult bindingResult) {
        positionValidator.validate(positionDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        positionService.create(convertToEntity(positionDTO));
        return findAll();
    }

    @PatchMapping("/{id}")
    public PageWrapper<PositionDTO> update(@PathVariable Long id, @RequestBody PositionDTO updatedPositionDTO) {
        Position existingPosition = positionService.readById(id)
                .orElseThrow(() -> new NotFoundException("Position not found with id: " + id));
        Position updatedPosition = convertToEntity(updatedPositionDTO);
        try {
            patcher.patch(existingPosition, updatedPosition);
            positionService.update(id, existingPosition);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return findAll();
    }

    @GetMapping("/{id}")
    public PositionDTO findById(@PathVariable Long id){
        return convertToDTO(positionService.readById(id).orElse(null));
    }

    @DeleteMapping("/{id}")
    public PageWrapper<PositionDTO> delete(@PathVariable Long id){
        positionService.delete(id);
        return findAll();
    }

    private Position convertToEntity(PositionDTO positionDTO) {
        return modelMapper.map(positionDTO, Position.class);
    }

    private PositionDTO convertToDTO(Position position){
        return modelMapper.map(position, PositionDTO.class);
    }
}

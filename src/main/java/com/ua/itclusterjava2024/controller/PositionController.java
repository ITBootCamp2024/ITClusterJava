package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.PositionDTO;
import com.ua.itclusterjava2024.entity.Position;
import com.ua.itclusterjava2024.exceptions.ValidationException;
import com.ua.itclusterjava2024.service.interfaces.PositionService;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import com.ua.itclusterjava2024.wrappers.Patcher;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/position")
public class PositionController {
    private final PositionService positionService;
    private final ModelMapper modelMapper;
    private final Patcher<Position> patcher;

    public PositionController(PositionService positionService, ModelMapper modelMapper, Patcher<Position> patcher) {
        this.positionService = positionService;
        this.modelMapper = modelMapper;
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
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        positionService.create(convertToEntity(positionDTO));
        return findAll();
    }

    @PatchMapping("/{id}")
    public PageWrapper<PositionDTO> update(@PathVariable Long id, @RequestBody PositionDTO updatedPositionDTO) {
        Position existingPosition = positionService.readById(id)
                .orElseThrow(() -> new EntityNotFoundException("Position not found with id: " + id));
        Position updatedPosition = convertToEntity(updatedPositionDTO);
        try {
            patcher.patch(existingPosition, updatedPosition);
            positionService.update(id, existingPosition);
        } catch (IllegalAccessException e) {
            log.error("Failed to update Position with id: {}", id, e);
        }
        return findAll();
    }

    @GetMapping("/{id}")
    public PositionDTO findById(@PathVariable Long id) {
        return convertToDTO(positionService.readById(id).orElse(null));
    }

    @DeleteMapping("/{id}")
    public PageWrapper<PositionDTO> delete(@PathVariable Long id) {
        positionService.delete(id);
        return findAll();
    }

    private Position convertToEntity(PositionDTO positionDTO) {
        return modelMapper.map(positionDTO, Position.class);
    }

    private PositionDTO convertToDTO(Position position) {
        return modelMapper.map(position, PositionDTO.class);
    }
}

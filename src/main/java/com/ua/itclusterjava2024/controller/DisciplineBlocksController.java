package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.DisciplineBlocksDTO;
import com.ua.itclusterjava2024.entity.DisciplineBlocks;
import com.ua.itclusterjava2024.exceptions.ValidationException;
import com.ua.itclusterjava2024.service.implementation.DisciplineBlocksServiceImpl;
import com.ua.itclusterjava2024.service.interfaces.DisciplineBlocksService;
import com.ua.itclusterjava2024.validators.CourseBlockValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
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

    public DisciplineBlocksController(DisciplineBlocksService disciplineBlocksService, ModelMapper modelMapper, CourseBlockValidator courseBlockValidator) {
        this.disciplineBlocksService = disciplineBlocksService;
        this.modelMapper = modelMapper;
        this.courseBlockValidator = courseBlockValidator;
    }
    @GetMapping
    public List<DisciplineBlocksDTO> showCourseBlockList(){
        return disciplineBlocksService.getAll().stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public RedirectView save(@RequestBody @Valid DisciplineBlocksDTO disciplineBlocksDTO,
                                        BindingResult bindingResult){
        courseBlockValidator.validate(disciplineBlocksDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        disciplineBlocksService.create(convertToEntity(disciplineBlocksDTO));
        return new RedirectView("redirect:/discipline-blocks");
    }

    @PatchMapping("/{id}")
    public RedirectView update(@PathVariable("id") Long id,
                                         @RequestBody @Valid DisciplineBlocksDTO disciplineBlocksDTO,
                                          BindingResult bindingResult){
        courseBlockValidator.validate(disciplineBlocksDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        disciplineBlocksService.update(id, convertToEntity(disciplineBlocksDTO));
        return new RedirectView("redirect:/discipline-blocks");
    }

    @GetMapping("/{id}")
    public DisciplineBlocksDTO findById(@PathVariable Long id){
        return convertToDTO(disciplineBlocksService.readById(id).orElse(null));
    }

    @DeleteMapping("/{id}")
    public RedirectView delete(@PathVariable Long id){
        disciplineBlocksService.delete(id);
        return new RedirectView("redirect:/discipline-blocks");
    }

    private DisciplineBlocks convertToEntity(DisciplineBlocksDTO disciplineBlocksDTO){
        return modelMapper.map(disciplineBlocksDTO, DisciplineBlocks.class);
    }

    private DisciplineBlocksDTO convertToDTO(DisciplineBlocks disciplineBlocks){
        return modelMapper.map(disciplineBlocks, DisciplineBlocksDTO.class);
    }
}

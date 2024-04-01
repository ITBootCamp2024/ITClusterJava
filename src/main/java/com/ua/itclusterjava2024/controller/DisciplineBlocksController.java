package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.CourseBlockDTO;
import com.ua.itclusterjava2024.entity.DisciplineBlocks;
import com.ua.itclusterjava2024.exceptions.ValidationException;
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
@RequestMapping("/course_blocks")
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
    public List<CourseBlockDTO> showCourseBlockList(){
        return disciplineBlocksService.getAll().stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public RedirectView saveCourseBlock(@RequestBody @Valid CourseBlockDTO courseBlockDTO,
                                        BindingResult bindingResult){
        courseBlockValidator.validate(courseBlockDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        disciplineBlocksService.create(convertToEntity(courseBlockDTO));
        return new RedirectView("redirect:/course_blocks");
    }

    @PatchMapping("/{id}")
    public RedirectView updateCourseBlock(@PathVariable("id") Long id,
                                         @RequestBody @Valid CourseBlockDTO courseBlockDTO,
                                          BindingResult bindingResult){
        courseBlockValidator.validate(courseBlockDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        disciplineBlocksService.update(id, convertToEntity(courseBlockDTO));
        return new RedirectView("redirect:/course_blocks");
    }

    @GetMapping("/{id}")
    public CourseBlockDTO findById(@PathVariable Long id){
        return convertToDTO(disciplineBlocksService.readById(id).orElse(null));
    }

    @DeleteMapping("/{id}")
    public RedirectView deleteCourseBlock(@PathVariable Long id){
        disciplineBlocksService.delete(id);
        return new RedirectView("redirect:/course_blocks");
    }

    private DisciplineBlocks convertToEntity(CourseBlockDTO courseBlockDTO){
        return modelMapper.map(courseBlockDTO, DisciplineBlocks.class);
    }

    private CourseBlockDTO convertToDTO(DisciplineBlocks disciplineBlocks){
        return modelMapper.map(disciplineBlocks, CourseBlockDTO.class);
    }
}

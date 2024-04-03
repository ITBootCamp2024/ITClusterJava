package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.DepartmentDTO;
import com.ua.itclusterjava2024.entity.Department;
import com.ua.itclusterjava2024.exceptions.ValidationException;
import com.ua.itclusterjava2024.service.interfaces.DepartmentService;
import com.ua.itclusterjava2024.validators.CourseBlockValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping( "/department")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final ModelMapper modelMapper;
    private final CourseBlockValidator courseBlockValidator;

    public DepartmentController(DepartmentService departmentService, ModelMapper modelMapper, CourseBlockValidator courseBlockValidator) {
        this.departmentService = departmentService;
        this.modelMapper = modelMapper;
        this.courseBlockValidator = courseBlockValidator;
    }
    @GetMapping
    public List<DepartmentDTO> showCourseBlockList(){
        return departmentService.getAll().stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public RedirectView save(@RequestBody @Valid DepartmentDTO departmentDTO,
                             BindingResult bindingResult){
        courseBlockValidator.validate(departmentDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        departmentService.create(convertToEntity(departmentDTO));
        return new RedirectView("/department");
    }

    @PatchMapping("/{id}")
    public RedirectView update(@PathVariable("id") Long id,
                               @RequestBody @Valid DepartmentDTO departmentDTO,
                               BindingResult bindingResult){
        courseBlockValidator.validate(departmentDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        departmentService.update(id, convertToEntity(departmentDTO));
        return new RedirectView("/department");
    }

    @GetMapping("/{id}")
    public DepartmentDTO findById(@PathVariable Long id){
        return convertToDTO(departmentService.readById(id).orElse(null));
    }

    @DeleteMapping("/{id}")
    public RedirectView delete(@PathVariable Long id){
        departmentService.delete(id);
        return new RedirectView("/department");
    }

    private Department convertToEntity(DepartmentDTO departmentDTO){
        return modelMapper.map(departmentDTO, Department.class);
    }

    private DepartmentDTO convertToDTO(Department department){
        return modelMapper.map(department, DepartmentDTO.class);
    }
}

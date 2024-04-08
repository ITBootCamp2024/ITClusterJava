package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.DepartmentDTO;
import com.ua.itclusterjava2024.dto.ServiceInfoDTO;
import com.ua.itclusterjava2024.dto.TeachersDTO;
import com.ua.itclusterjava2024.dto.UniversityDTO;
import com.ua.itclusterjava2024.entity.Department;
import com.ua.itclusterjava2024.entity.University;
import com.ua.itclusterjava2024.exceptions.NotFoundException;
import com.ua.itclusterjava2024.exceptions.ValidationException;
import com.ua.itclusterjava2024.service.interfaces.DepartmentService;
import com.ua.itclusterjava2024.service.interfaces.UniversityService;
import com.ua.itclusterjava2024.validators.CourseBlockValidator;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import com.ua.itclusterjava2024.wrappers.Patcher;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping( "/department")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final UniversityService universityService;
    private final ModelMapper modelMapper;

    @Autowired
    Patcher patcher;
    private final CourseBlockValidator courseBlockValidator;

    public DepartmentController(DepartmentService departmentService, UniversityService universityService, ModelMapper modelMapper, CourseBlockValidator courseBlockValidator) {
        this.departmentService = departmentService;
        this.universityService = universityService;
        this.modelMapper = modelMapper;
        this.courseBlockValidator = courseBlockValidator;
    }
    @GetMapping()
    public PageWrapper<DepartmentDTO> findAll(){
        List<DepartmentDTO> departments = departmentService.getAll().stream()
                .map(this::convertToDTO)
                .toList();

        List<UniversityDTO> universityDTOs = universityService.getAll()
                .stream()
                .map(u -> UniversityDTO.builder()
                        .id(u.getId())
                        .name(u.getName())
                        .abbr(u.getAbbr())
                        .build())
                .collect(Collectors.toList());

        PageWrapper<DepartmentDTO> pageWrapper = new PageWrapper<>();
        pageWrapper.setContent(departments);
        pageWrapper.setService_info(ServiceInfoDTO.builder().university(universityDTOs).build());
        pageWrapper.setTotalElements(departments.size());
        return pageWrapper;
    }

    @PostMapping
    public PageWrapper<DepartmentDTO> save(@RequestBody DepartmentDTO departmentDTO,
                             BindingResult bindingResult){
//        courseBlockValidator.validate(departmentDTO, bindingResult);
//        if (bindingResult.hasErrors()){
//            throw new ValidationException(bindingResult);
//        }
        departmentService.create(convertToEntity(departmentDTO));
        return findAll();
    }

    @PatchMapping("/{id}")
    public PageWrapper<DepartmentDTO> update(@PathVariable("id") Long id,
                                             @RequestBody Department departments,
                                             BindingResult bindingResult){
//        courseBlockValidator.validate(departmentDTO, bindingResult);
//        if (bindingResult.hasErrors()){
//            throw new ValidationException(bindingResult);
//        }
        Department department = departmentService.readById(id).orElse(null);

        try{
            patcher.patch(department, departments);
            departmentService.create(department);
        }catch(Exception e){
            e.printStackTrace();
        }
        return findAll();
    }

    @GetMapping("/{id}")
    public DepartmentDTO findById(@PathVariable Long id){
        return convertToDTO(departmentService.readById(id).orElse(null));
    }

    @DeleteMapping("/{id}")
    public PageWrapper<DepartmentDTO> delete(@PathVariable Long id){
        departmentService.delete(id);
        return findAll();
    }

    private Department convertToEntity(DepartmentDTO departmentDTO){
        Department department = modelMapper.map(departmentDTO, Department.class);
        University university = universityService.readById(departmentDTO.getUniversity().getId())
                .orElseThrow(() -> new NotFoundException("University is not found"));

        department.setUniversity(university);
        return department;
    }

    private DepartmentDTO convertToDTO(Department department){
        DepartmentDTO departmentDTO = modelMapper.map(department, DepartmentDTO.class);

        if (department.getPhone() != null && !department.getPhone().isEmpty()) {
            List<String> phonesList = Arrays.asList(department.getPhone().split("\\s*,\\s*"));
            departmentDTO.setPhone(phonesList);
        }
        departmentDTO.setUniversity(UniversityDTO.builder()
                .id(department.getUniversity().getId())
                .name(department.getUniversity().getName()).build());
        return departmentDTO;
    }
}

package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.DepartmentDTO;
import com.ua.itclusterjava2024.dto.ServiceInfoDTO;
import com.ua.itclusterjava2024.dto.UniversityDTO;
import com.ua.itclusterjava2024.entity.Department;
import com.ua.itclusterjava2024.entity.University;
import com.ua.itclusterjava2024.service.implementation.ServiceInfoService;
import com.ua.itclusterjava2024.service.interfaces.DepartmentService;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import com.ua.itclusterjava2024.wrappers.Patcher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/department")
@Slf4j
public class DepartmentController {
    private final DepartmentService departmentService;
    private final ModelMapper modelMapper;
    private final ServiceInfoService serviceInfoService;
    private final EntityManager entityManager;
    private final Patcher<Department> patcher;

    @Autowired
    public DepartmentController(DepartmentService departmentService, ModelMapper modelMapper, ServiceInfoService serviceInfoService, EntityManager entityManager, Patcher<Department> patcher) {
        this.departmentService = departmentService;
        this.modelMapper = modelMapper;
        this.serviceInfoService = serviceInfoService;
        this.entityManager = entityManager;
        this.patcher = patcher;
    }

    @GetMapping()
    public PageWrapper<DepartmentDTO> findAll() {
        List<DepartmentDTO> departmentsPage = departmentService.getAll().stream()
                .map(this::convertToDTO)
                .toList();

        PageWrapper<DepartmentDTO> pageWrapper = new PageWrapper<>();
        pageWrapper.setContent(departmentsPage);
        pageWrapper.setServiceInfo(prepareServiceInfo());
        pageWrapper.setTotalElements(departmentsPage.size());
        return pageWrapper;
    }

    @PostMapping
    public PageWrapper<DepartmentDTO> save(@RequestBody DepartmentDTO departmentDTO) {
        departmentService.create(convertToEntity(departmentDTO));
        entityManager.clear();
        return findAll();
    }

    @PatchMapping("/{id}")
    public PageWrapper<DepartmentDTO> update(@PathVariable("id") Long id,
                                             @RequestBody DepartmentDTO departmentsDTO) {
        Department existingDepartment = departmentService.readById(id).
                orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + id));
        Department updatedDepartment = convertToEntity(departmentsDTO);
        try {
            patcher.patch(existingDepartment, updatedDepartment);
            departmentService.update(id, existingDepartment);
        } catch (IllegalAccessException e) {
            log.error("Failed to update department with id: {}", id, e);
        }
        entityManager.clear();
        return findAll();
    }

    @GetMapping("/{id}")
    public DepartmentDTO findById(@PathVariable Long id) {
        return convertToDTO(departmentService.readById(id).orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + id)));
    }

    @DeleteMapping("/{id}")
    public PageWrapper<DepartmentDTO> delete(@PathVariable Long id) {
        departmentService.delete(id);
        return findAll();
    }

    private Department convertToEntity(DepartmentDTO departmentDTO) {
        Department department = modelMapper.map(departmentDTO, Department.class);

        if (departmentDTO.getPhone() != null && !departmentDTO.getPhone().isEmpty()) {
            String phoneNumbers = String.join(",", departmentDTO.getPhone());
            department.setPhone(phoneNumbers);
        }

        if (departmentDTO.getUniversity() != null) {
            University university = modelMapper.map(departmentDTO.getUniversity(), University.class);
            department.setUniversity(university);
        }
        return department;
    }

    private DepartmentDTO convertToDTO(Department department) {
        DepartmentDTO departmentDTO = modelMapper.map(department, DepartmentDTO.class);

        if (department.getPhone() != null && !department.getPhone().isEmpty()) {
            departmentDTO.setPhone(Arrays.asList(department.getPhone().split("\\s*,\\s*")));
        }
        departmentDTO.setUniversity(UniversityDTO.builder()
                .id(department.getUniversity().getId())
                .name(department.getUniversity().getName()).build());
        return departmentDTO;
    }

    private List<UniversityDTO> prepareUniversities() {
        return serviceInfoService.getAllUniversities().stream()
                .map(u -> UniversityDTO.builder()
                        .id(u.getId())
                        .name(u.getName())
                        .abbr(u.getAbbr())
                        .build())
                .toList();
    }

    private ServiceInfoDTO prepareServiceInfo() {
        ServiceInfoDTO serviceInfo = new ServiceInfoDTO();
        serviceInfo.setUniversity(prepareUniversities());
        return serviceInfo;
    }
}

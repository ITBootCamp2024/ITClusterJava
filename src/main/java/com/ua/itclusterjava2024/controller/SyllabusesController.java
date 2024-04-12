package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.ServiceInfoDTO;
import com.ua.itclusterjava2024.dto.SyllabusesDTO;
import com.ua.itclusterjava2024.entity.Syllabuses;
import com.ua.itclusterjava2024.exceptions.NotFoundException;
import com.ua.itclusterjava2024.service.implementation.ServiceInfoService;
import com.ua.itclusterjava2024.service.interfaces.SyllabusesService;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import com.ua.itclusterjava2024.wrappers.Patcher;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// TODO
// controller according to others. There aren't endpoints in documentation
@RestController
@RequestMapping("/syllabuses")
public class SyllabusesController {

//    private final SyllabusesService syllabusesService;
//    private final ModelMapper modelMapper;
//    private final ServiceInfoService serviceInfoService;
//    private final EntityManager entityManager;
//    private final Patcher<Syllabuses> patcher;
//
//    @Autowired
//    public SyllabusesController(SyllabusesService syllabusesService, ModelMapper modelMapper, ServiceInfoService serviceInfoService, EntityManager entityManager, Patcher<Syllabuses> patcher) {
//        this.syllabusesService = syllabusesService;
//        this.modelMapper = modelMapper;
//        this.serviceInfoService = serviceInfoService;
//        this.entityManager = entityManager;
//        this.patcher = patcher;
//    }
//
//    @GetMapping()
//    public PageWrapper<SyllabusesDTO> findAll() {
//        List<SyllabusesDTO> syllabusesPage = syllabusesService.getAll().stream()
//                .map(this::convertToDTO)
//                .toList();
//
//        PageWrapper<SyllabusesDTO> pageWrapper = new PageWrapper<>();
//        pageWrapper.setContent(syllabusesPage);
//        pageWrapper.setService_info(prepareServiceInfo());
//        pageWrapper.setTotalElements(syllabusesPage.size());
//        return pageWrapper;
//    }
//
//    @PostMapping
//    public PageWrapper<SyllabusesDTO> save(@RequestBody SyllabusesDTO syllabusesDTO) {
//        syllabusesService.create(convertToEntity(syllabusesDTO));
//        entityManager.clear();
//        return findAll();
//    }
//
//    @PatchMapping("/{id}")
//    public PageWrapper<SyllabusesDTO> update(@PathVariable("id") Long id,
//                                             @RequestBody SyllabusesDTO syllabusesDTO) {
//        Syllabuses existingSyllabus = syllabusesService.readById(id).
//                orElseThrow(() -> new NotFoundException("Syllabuses not found with id: " + id));
//        Syllabuses updatedSyllabus = convertToEntity(syllabusesDTO);
//        try {
//            patcher.patch(existingSyllabus, updatedSyllabus);
//            syllabusesService.update(id, existingSyllabus);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        entityManager.clear();
//        return findAll();
//    }
//
//    @GetMapping("/{id}")
//    public SyllabusesDTO findById(@PathVariable Long id) {
//        return convertToDTO(syllabusesService.readById(id).orElse(null));
//    }
//
//    @DeleteMapping("/{id}")
//    public PageWrapper<SyllabusesDTO> delete(@PathVariable Long id) {
//        syllabusesService.delete(id);
//        return findAll();
//    }
//
//    private Syllabuses convertToEntity(SyllabusesDTO departmentDTO) {
//        return modelMapper.map(departmentDTO, Syllabuses.class);
//    }
//
//    private SyllabusesDTO convertToDTO(Syllabuses department) {
//        return modelMapper.map(department, SyllabusesDTO.class);
//    }
//
//    private ServiceInfoDTO prepareServiceInfo() {
//        ServiceInfoDTO serviceInfo = new ServiceInfoDTO();
//        return serviceInfo;
//    }
}

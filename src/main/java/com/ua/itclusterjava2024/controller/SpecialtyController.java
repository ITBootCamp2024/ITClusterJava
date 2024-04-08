package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.SpecialtyDTO;
import com.ua.itclusterjava2024.dto.TeachersDTO;
import com.ua.itclusterjava2024.entity.Specialty;
import com.ua.itclusterjava2024.exceptions.ValidationException;
import com.ua.itclusterjava2024.service.interfaces.SpecialtyService;
import com.ua.itclusterjava2024.validators.SpecialtyValidator;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import com.ua.itclusterjava2024.wrappers.Patcher;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/specialties")
public class SpecialtyController {

    private final SpecialtyService specialtyService;
    private final ModelMapper modelMapper;
    private final SpecialtyValidator specialtyValidator;

    @Autowired
    Patcher patcher;

    @Autowired
    public SpecialtyController(SpecialtyService specialtyService, ModelMapper modelMapper, SpecialtyValidator specialtyValidator) {
        this.specialtyService = specialtyService;
        this.modelMapper = modelMapper;
        this.specialtyValidator = specialtyValidator;
    }

    @GetMapping
    public PageWrapper<SpecialtyDTO> findAll(@RequestParam(defaultValue = "1") int page) {
        int pageSize = 20;
        PageRequest pageable = PageRequest.of(page - 1, pageSize);
        Page<SpecialtyDTO> specialtyPage =  specialtyService.getAll(pageable).map(this::convertToDTO);

        PageWrapper<SpecialtyDTO> pageWrapper = new PageWrapper<>();
        pageWrapper.setContent(specialtyPage.getContent());
        pageWrapper.setTotalElements(specialtyPage.getTotalElements());
        return pageWrapper;
    }

    @GetMapping("/{id}")
    public SpecialtyDTO findById(@PathVariable long id) {
        return convertToDTO(specialtyService.readById(id).orElse(null));
    }

    @PostMapping
    public PageWrapper<SpecialtyDTO> save(@RequestBody @Valid SpecialtyDTO specialtyDTO, BindingResult bindingResult) {
//        specialtyValidator.validate(specialtyDTO, bindingResult);
//        if (bindingResult.hasErrors()){
//            throw new ValidationException(bindingResult);
//        }
        specialtyService.create(convertToEntity(specialtyDTO));
        return findAll(1);//new RedirectView("/specialties");
    }

    @PatchMapping("/{id}")
    public PageWrapper<SpecialtyDTO> update(@PathVariable("id") Long id,
            @RequestBody Specialty specialties//, BindingResult bindingResult
    )
    {
//        specialtyValidator.validate(specialtyDTO, bindingResult);
//        if (bindingResult.hasErrors()){
//            throw new ValidationException(bindingResult);
//        }
//         specialtyService.update(id, convertToEntity(specialtyDTO));

        Specialty specialty = specialtyService.readById(id).orElse(null);
        try{
            patcher.patch(specialty, specialties);
            specialtyService.create(specialty);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return findAll(1);//new RedirectView("/specialties");
    }

    @DeleteMapping("/{id}")
    public PageWrapper<SpecialtyDTO> delete(@PathVariable long id) {
        specialtyService.delete(id);
        return findAll(1);//new RedirectView("/specialties");
    }

    private Specialty convertToEntity(SpecialtyDTO specialtyDTO){
        return modelMapper.map(specialtyDTO, Specialty.class);
    }

    private SpecialtyDTO convertToDTO(Specialty specialty){
        return modelMapper.map(specialty, SpecialtyDTO.class);
    }
}

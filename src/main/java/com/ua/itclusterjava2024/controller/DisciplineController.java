package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.*;
import com.ua.itclusterjava2024.entity.*;
import com.ua.itclusterjava2024.service.interfaces.*;
import com.ua.itclusterjava2024.validators.CourseValidator;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import com.ua.itclusterjava2024.wrappers.Patcher;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/disciplines")
public class DisciplineController {
    private final DisciplinesService disciplinesService;
    private final DisciplineGroupService disciplineGroupService;
    private final EducationProgramsService educationProgramsService;
    private final TeachersService teachersService;
    private final ModelMapper modelMapper;
    private final CourseValidator disciplinesValidator;
    @Autowired
    Patcher patcher;

    @Autowired
    public DisciplineController(DisciplinesService disciplineService, DisciplineGroupService disciplineGroupService, EducationProgramsService educationProgramsService, ModelMapper modelMapper, CourseValidator disciplinesValidator, DisciplinesService disciplinesService, TeachersService teachersService) {
        this.disciplinesService = disciplineService;
        this.disciplineGroupService = disciplineGroupService;
        this.educationProgramsService = educationProgramsService;
        this.modelMapper = modelMapper;
        this.disciplinesValidator = disciplinesValidator;
        this.teachersService = teachersService;
    }

    @GetMapping
    public PageWrapper<DisciplinesDTO> findAll(@RequestParam(defaultValue = "1") int page) {
        int pageSize = 20;
        PageRequest pageable = PageRequest.of(page - 1, pageSize);
        Page<DisciplinesDTO> disciplinesPage = disciplinesService.getAll(pageable).map(this::convertToDTO);

        PageWrapper<DisciplinesDTO> pageWrapper = new PageWrapper<>();
        pageWrapper.setContent(disciplinesPage.getContent());
        pageWrapper.setTotalElements(disciplinesPage.getTotalElements());
        return pageWrapper;
    }

    @GetMapping("/{id}")
    public DisciplinesDTO findById(@PathVariable long id) {
        return convertToDTO(disciplinesService.readById(id).orElse(null));
    }

    @PostMapping
    public PageWrapper<DisciplinesDTO> save(@RequestBody @Valid DisciplinesDTO disciplinesDTO,
                                            BindingResult bindingResult) {
        disciplinesService.create(convertToEntity(disciplinesDTO));
        return findAll(1);
    }

    @PatchMapping("/{id}")
    public PageWrapper<DisciplinesDTO> update(@PathVariable("id") Long id,
                                              @RequestBody Disciplines disciplines,
                                              BindingResult bindingResult
    ) {
        Disciplines existingTeacher = disciplinesService.readById(id).orElse(null);
        try {
            patcher.patch(existingTeacher, disciplines);
            disciplinesService.create(existingTeacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return findAll(1);
    }

    @DeleteMapping("/{id}")
    public PageWrapper<DisciplinesDTO> delete(@PathVariable long id) {
        disciplinesService.delete(id);
        return findAll(1);
    }

    private Disciplines convertToEntity(DisciplinesDTO dto) {
        Disciplines disciplines = modelMapper.map(dto, Disciplines.class);
        disciplines.setSyllabus_url(dto.getSyllabus_url());
        disciplines.setEducation_plan_url(dto.getEducation_plan_url());

        Teachers teacher = teachersService.readById(dto.getTeacher().getId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        disciplines.setTeachers(teacher);

        DisciplineGroups disciplineGroup = disciplineGroupService.readById(dto.getDiscipline_group().getId())
                .orElseThrow(() -> new RuntimeException("Discipline group not found"));
        disciplines.setDiscipline_group(disciplineGroup);

        EducationPrograms educationPrograms = educationProgramsService.readById(dto.getEducation_program().getId())
                .orElseThrow(() -> new RuntimeException("Education program not found"));
        disciplines.setEducation_program(educationPrograms);

        return disciplines;    }

    private DisciplinesDTO convertToDTO(Disciplines disciplines) {
        DisciplinesDTO dto = modelMapper.map(disciplines, DisciplinesDTO.class);
        dto.setTeacher(TeachersDTO.builder().id(disciplines.getTeachers().getId())
                .name(disciplines.getTeachers().getName()).build());
        dto.setDiscipline_block(DisciplineBlocksDTO.builder().id(disciplines.getDiscipline_group().getBlock_id().getId())
                .name(disciplines.getDiscipline_group().getBlock_id().getName()).build());
        dto.setDiscipline_group(DisciplineGroupsDTO.builder().id(disciplines.getDiscipline_group().getId())
                .name(disciplines.getDiscipline_group().getName()).build());
        dto.setEducation_program(EducationProgramsDTO.builder().id(disciplines.getEducation_program().getId())
                .name(disciplines.getEducation_program().getName())
                .program_url(disciplines.getEducation_program().getProgram_url()).build());
        dto.setSyllabus_url(disciplines.getSyllabus_url());
        dto.setEducation_plan_url(disciplines.getEducation_plan_url());
        return dto;
    }
}

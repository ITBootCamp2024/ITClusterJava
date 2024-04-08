package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.*;
import com.ua.itclusterjava2024.entity.*;
import com.ua.itclusterjava2024.service.implementation.ServiceInfoService;
import com.ua.itclusterjava2024.service.interfaces.DisciplineGroupService;
import com.ua.itclusterjava2024.service.interfaces.DisciplinesService;
import com.ua.itclusterjava2024.service.interfaces.EducationProgramsService;
import com.ua.itclusterjava2024.service.interfaces.TeachersService;
import com.ua.itclusterjava2024.validators.CourseValidator;
import com.ua.itclusterjava2024.wrappers.PageWrapper;
import com.ua.itclusterjava2024.wrappers.Patcher;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplines")
public class DisciplineController {
    private final DisciplinesService disciplinesService;
    private final DisciplineGroupService disciplineGroupService;
    private final EducationProgramsService educationProgramsService;
    private final TeachersService teachersService;
    private final ServiceInfoService serviceInfoService;
    private final ModelMapper modelMapper;
    private final CourseValidator disciplinesValidator;
    @Autowired
    Patcher patcher;

    @Autowired
    public DisciplineController(DisciplinesService disciplineService, DisciplineGroupService disciplineGroupService, EducationProgramsService educationProgramsService, ModelMapper modelMapper, CourseValidator disciplinesValidator, TeachersService teachersService, ServiceInfoService serviceInfoService) {
        this.disciplinesService = disciplineService;
        this.disciplineGroupService = disciplineGroupService;
        this.educationProgramsService = educationProgramsService;
        this.modelMapper = modelMapper;
        this.disciplinesValidator = disciplinesValidator;
        this.teachersService = teachersService;
        this.serviceInfoService = serviceInfoService;
    }

    @GetMapping
    public PageWrapper<DisciplinesDTO> findAll() {
        List<DisciplinesDTO> disciplines = disciplinesService.getAll().stream()
                .map(this::convertToDTO)
                .toList();

        ServiceInfoDTO serviceInfo = prepareServiceInfo();

        PageWrapper<DisciplinesDTO> pageWrapper = new PageWrapper<>();
        pageWrapper.setContent(disciplines);
        pageWrapper.setService_info(serviceInfo);
        pageWrapper.setTotalElements(disciplines.size());

        return pageWrapper;
    }


    /*@GetMapping("/{id}")
    public DisciplinesDTO findById(@PathVariable long id) {
        return convertToDTO(disciplinesService.readById(id).orElse(null));
    }*/

    @PostMapping
    public PageWrapper<DisciplinesDTO> save(@RequestBody @Valid DisciplinesDTO disciplinesDTO,
                                            BindingResult bindingResult) {
        disciplinesService.create(convertToEntity(disciplinesDTO));
        return findAll();
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
        return findAll();
    }

    @DeleteMapping("/{id}")
    public PageWrapper<DisciplinesDTO> delete(@PathVariable long id) {
        disciplinesService.delete(id);
        return findAll();
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

        return disciplines;
    }

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

    private ServiceInfoDTO prepareServiceInfo() {
        ServiceInfoDTO serviceInfo = new ServiceInfoDTO();
        serviceInfo.setTeachers(prepareTeachers());
        serviceInfo.setEducation_program(prepareEducationPrograms());
        serviceInfo.setDisciplineBlocks(prepareDisciplineBlocks());
        return serviceInfo;
    }

    private List<TeachersDTO> prepareTeachers() {
        return serviceInfoService.getAllTeachers().stream()
                .map(teacher -> TeachersDTO.builder().id(teacher.getId()).name(teacher.getName()).build())
                .toList();
    }

    private List<EducationProgramsDTO> prepareEducationPrograms() {
        return serviceInfoService.getAllEducationPrograms().stream()
                .map(program -> EducationProgramsDTO.builder().id(program.getId()).name(program.getName()).program_url(program.getProgram_url()).build())
                .toList();
    }

    private List<DisciplineBlocksDTO> prepareDisciplineBlocks() {
        return serviceInfoService.getAllDisciplineBlocks().stream()
                .map(block -> DisciplineBlocksDTO.builder().id(block.getId()).name(block.getName())
                        .disciplineGroups(prepareDisciplineGroups(block))
                        .build())
                .toList();
    }

    private List<DisciplineGroupsDTO> prepareDisciplineGroups(DisciplineBlocks block) {
        return serviceInfoService.getAllDisciplineGroupsByDisciplineBlocksId(block).stream()
                .map(group -> DisciplineGroupsDTO.builder().id(group.getId()).name(group.getName()).build())
                .toList();
    }
}

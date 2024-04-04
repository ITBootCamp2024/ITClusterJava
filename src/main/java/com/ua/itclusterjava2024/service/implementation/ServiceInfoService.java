package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.*;
import com.ua.itclusterjava2024.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceInfoService {
    private final PositionRepository positionRepository;
    private final EducationLevelsRepository educationLevelRepository;
    private final TeachersRepository teacherRepository;
    private final UniversityRepository universityRepository;
    private final DepartmentRepository departmentRepository;
    private final SpecialtyRepository specialtyRepository;
    private final DisciplineGroupsRepository disciplineGroupsRepository;
    private final DisciplinesRepository disciplinesRepository;
    private final DisciplineBlocksRepository disciplineBlocksRepository;
    private final EducationProgramsRepository educationProgramsRepository;

    @Autowired
    public ServiceInfoService(PositionRepository positionRepository, EducationLevelsRepository educationLevelRepository, TeachersRepository teacherRepository, UniversityRepository universityRepository, DepartmentRepository departmentRepository, SpecialtyRepository specialtyRepository, DisciplineGroupsRepository disciplineGroupsRepository, DisciplinesRepository disciplinesRepository, DisciplineBlocksRepository disciplineBlocksRepository, EducationProgramsRepository educationProgramsRepository) {
        this.positionRepository = positionRepository;
        this.educationLevelRepository = educationLevelRepository;
        this.teacherRepository = teacherRepository;
        this.universityRepository = universityRepository;
        this.departmentRepository = departmentRepository;
        this.specialtyRepository = specialtyRepository;
        this.disciplineGroupsRepository = disciplineGroupsRepository;
        this.disciplinesRepository = disciplinesRepository;
        this.disciplineBlocksRepository = disciplineBlocksRepository;
        this.educationProgramsRepository = educationProgramsRepository;
    }

    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    public List<EducationLevel> getAllEducationLevels() {
        return educationLevelRepository.findAll();
    }

    public List<Teachers> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    public List<Department> getAllDepartmentsByUniversityId(Long universityId) {
        return departmentRepository.findByUniversityId(universityId);
    }

    public List<Specialty> getAllSpecialties() {
        return specialtyRepository.findAll();
    }

    public List<Disciplines> getAllDisciplines() {
        return disciplinesRepository.findAll();
    }

    public List<DisciplineBlocks> getAllDisciplineBlocks() {
        return disciplineBlocksRepository.findAll();
    }

    public List<EducationPrograms> getAllEducationPrograms() {
        return educationProgramsRepository.findAll();
    }

    public List<DisciplineGroups> getAllDisciplineGroupsByDisciplineBlocksId(DisciplineBlocks disciplineBlocksId) {
        return disciplineGroupsRepository.findByBlockId(disciplineBlocksId);
    }
}

package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.CourseGroup;
import com.ua.itclusterjava2024.entity.School;
import com.ua.itclusterjava2024.repository.CourseGroupRepository;
import com.ua.itclusterjava2024.service.interfaces.CourseGroupService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseGroupServiceImpl implements CourseGroupService {

    private final CourseGroupRepository courseGroupRepository;

    public CourseGroupServiceImpl(CourseGroupRepository courseGroupRepository) {
        this.courseGroupRepository = courseGroupRepository;
    }

    @Override
    public CourseGroup create(CourseGroup courseGroup) {
        return courseGroupRepository.save(courseGroup);
    }

    @Override
    public CourseGroup readById(long id) {
        Optional<CourseGroup> foundSchool = courseGroupRepository.findById(id);
        return foundSchool.orElse(null);
    }

    @Override
    public CourseGroup update(CourseGroup courseGroup) {
        return courseGroupRepository.save(courseGroup);
    }

    @Override
    public void delete(long id) {
        courseGroupRepository.deleteById(id);
    }

    @Override
    public List<CourseGroup> getAll() {
        return courseGroupRepository.findAll();
    }
}

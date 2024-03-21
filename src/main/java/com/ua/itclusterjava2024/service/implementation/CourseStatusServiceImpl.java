package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.CourseStatus;
import com.ua.itclusterjava2024.entity.School;
import com.ua.itclusterjava2024.repository.CourseStatusRepository;
import com.ua.itclusterjava2024.service.interfaces.CourseStatusService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseStatusServiceImpl implements CourseStatusService {

    private final CourseStatusRepository courseStatusRepository;

    public CourseStatusServiceImpl(CourseStatusRepository courseStatusRepository) {
        this.courseStatusRepository = courseStatusRepository;
    }

    @Override
    public CourseStatus create(CourseStatus courseStatus) {
        return courseStatusRepository.save(courseStatus);
    }

    @Override
    public CourseStatus readById(long id) {
        Optional<CourseStatus> foundSchool = courseStatusRepository.findById(id);
        return foundSchool.orElse(null);
    }

    @Override
    public CourseStatus update(CourseStatus courseStatus) {
        return courseStatusRepository.save(courseStatus);
    }

    @Override
    public void delete(long id) {
        courseStatusRepository.deleteById(id);
    }

    @Override
    public List<CourseStatus> getAll() {
        return courseStatusRepository.findAll();
    }
}

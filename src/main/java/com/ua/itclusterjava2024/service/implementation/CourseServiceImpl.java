package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.Course;
import com.ua.itclusterjava2024.repository.CourseRepository;
import com.ua.itclusterjava2024.service.interfaces.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course create(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course readById(long id) {
        return courseRepository.getById(id);
    }

    @Override
    public Course update(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void delete(long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }
}

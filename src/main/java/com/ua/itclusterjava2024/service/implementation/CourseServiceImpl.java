package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.Course;
import com.ua.itclusterjava2024.repository.CourseRepository;
import com.ua.itclusterjava2024.service.interfaces.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Course> readById(long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course update(long id, Course course) {
        course.setId(id);
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

    @Override
    public Page<Course> getAll(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }
}

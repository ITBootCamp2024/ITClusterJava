package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.CourseBlock;
import com.ua.itclusterjava2024.repository.CourseBlockRepository;
import com.ua.itclusterjava2024.service.interfaces.CourseBlockService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseBlockServiceImpl implements CourseBlockService {

    private final CourseBlockRepository courseBlockRepository;

    public CourseBlockServiceImpl(CourseBlockRepository courseBlockRepository) {
        this.courseBlockRepository = courseBlockRepository;
    }

    @Override
    public CourseBlock create(CourseBlock courseBlock) {
        return courseBlockRepository.save(courseBlock);
    }

    @Override
    public CourseBlock readById(long id) {
        return courseBlockRepository.findCourseBlockById(id);
    }

    @Override
    public CourseBlock update(CourseBlock courseBlock) {
        return courseBlockRepository.save(courseBlock);
    }

    @Override
    public void delete(long id) {
        courseBlockRepository.deleteById(id);
    }

    @Override
    public List<CourseBlock> getAll() {
        return courseBlockRepository.findAll();
    }
}

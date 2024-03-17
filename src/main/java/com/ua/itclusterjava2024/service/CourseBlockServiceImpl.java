package com.ua.itclusterjava2024.service;

import com.ua.itclusterjava2024.entity.CourseBlock;
import com.ua.itclusterjava2024.repositories.CourseBlockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseBlockServiceImpl implements CourseBlockService {

    private final CourseBlockRepository courseBlockRepository;

    public CourseBlockServiceImpl(CourseBlockRepository courseBlockRepository) {
        this.courseBlockRepository = courseBlockRepository;
    }

    public List<CourseBlock> findAll() {
        return courseBlockRepository.findAll();
    }

    @Override
    public CourseBlock saveCourseBlock(CourseBlock courseBlock) {
        return courseBlockRepository.save(courseBlock);
    }

    @Override
    public CourseBlock updateCourseBlock(Long id, CourseBlock courseBlock) {
       // Not finished

//        courseBlockRepository.findCourseBlockById(id);
        courseBlock.setId(id);
        return courseBlockRepository.save(courseBlock);
    }

    @Override
    public void deleteCourseBlockById(Long id) {
        courseBlockRepository.deleteById(id);
    }
}

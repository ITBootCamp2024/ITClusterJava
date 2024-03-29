package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.CourseBlock;
import com.ua.itclusterjava2024.repository.CourseBlockRepository;
import com.ua.itclusterjava2024.service.interfaces.CourseBlockService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        Optional<CourseBlock> foundSchool = courseBlockRepository.findById(id);
        return foundSchool.orElse(null);
    }

    @Override
    public CourseBlock update(long id, CourseBlock courseBlock) {
        courseBlock.setId(id);
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

    @Override
    public Page<CourseBlock> getAll(Pageable pageable) {
        return courseBlockRepository.findAll(pageable);
    }

}

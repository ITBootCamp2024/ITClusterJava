package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.CourseStatus;
import com.ua.itclusterjava2024.repository.CourseStatusRepository;
import com.ua.itclusterjava2024.service.interfaces.CourseStatusService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Optional<CourseStatus> readById(long id) {
        return courseStatusRepository.findById(id);
    }

    @Override
    public CourseStatus update(long id, CourseStatus courseStatus) {
        courseStatus.setId(id);
        return courseStatusRepository.save(courseStatus);
    }

    @Override
    public void delete(long id) {
        courseStatusRepository.deleteById(id);
    }

    @Override
    public Page<CourseStatus> getAll(Pageable pageable) {
        return courseStatusRepository.findAll(pageable);
    }

    @Override
    public List<CourseStatus> getAll() {
        return courseStatusRepository.findAll();
    }
}

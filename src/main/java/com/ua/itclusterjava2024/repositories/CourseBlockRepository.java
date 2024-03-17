package com.ua.itclusterjava2024.repositories;

import com.ua.itclusterjava2024.entity.CourseBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseBlockRepository extends JpaRepository<CourseBlock, Long> {
    CourseBlock findCourseBlockById(Long id);
}

package com.gussoft.school_mito.core.repository;

import com.gussoft.school_mito.core.models.Course;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends GenericRepository<Course, String> {

}

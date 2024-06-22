package com.gussoft.school_mito.core.business.impl;

import com.gussoft.school_mito.core.business.CourseService;
import com.gussoft.school_mito.core.models.Course;
import com.gussoft.school_mito.core.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CourseServiceImpl extends GenericServiceImpl<Course, String> implements CourseService {

    @Autowired
    public CourseServiceImpl(CourseRepository repository) {
        super(repository);
    }

    @Override
    protected void setId(Course request, String id) {
        request.setId(id);
    }
}

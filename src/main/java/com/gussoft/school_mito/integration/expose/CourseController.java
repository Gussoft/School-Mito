package com.gussoft.school_mito.integration.expose;

import com.gussoft.school_mito.core.business.GenericService;
import com.gussoft.school_mito.core.models.Course;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin("*")
@RequestMapping(path = "/courses")
public class CourseController extends GenericController<Course, String> {

    public CourseController(GenericService<Course, String> service) {
        super(service);
    }

    @Override
    protected String getIdEntity(Course data) {
        return data.getId();
    }

}

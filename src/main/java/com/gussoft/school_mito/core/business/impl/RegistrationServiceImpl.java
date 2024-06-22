package com.gussoft.school_mito.core.business.impl;

import com.gussoft.school_mito.core.business.CourseService;
import com.gussoft.school_mito.core.business.RegistrationService;
import com.gussoft.school_mito.core.business.StudentService;
import com.gussoft.school_mito.core.models.Course;
import com.gussoft.school_mito.core.models.Registration;
import com.gussoft.school_mito.core.models.Student;
import com.gussoft.school_mito.core.repository.RegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RegistrationServiceImpl extends GenericServiceImpl<Registration, String> implements RegistrationService {

    private final CourseService courseService;
    private final StudentService studentService;
    private final RegistrationRepository repository;

    @Autowired
    public RegistrationServiceImpl(RegistrationRepository repository,
                                   CourseService courseService, StudentService studentService) {
        super(repository);
        this.courseService = courseService;
        this.studentService = studentService;
        this.repository = repository;
    }

    @Override
    protected void setId(Registration request, String id) {
        request.setId(id);
    }


    @Override
    public Mono<Registration> findByIdRegistration(String id) {
        return repository.findById(id)
                .flatMap(this::populateStudent)
                .flatMap(this::populateCourses);
    }

    private Mono<Registration> populateStudent(Registration obj) {
        return studentService.findById(obj.getStudent().getId())
                .map(data -> {
                    obj.setStudent(data);
                    return obj;
                });
    }

    private Mono<Registration> populateCourses(Registration obj) {
        Flux<Course> data = Flux.fromIterable(obj.getCourses())
                .flatMap(course -> courseService.findById(course.getId()));

        return data.collectList()
                .map(courses -> {
                    obj.setCourses(courses);
                    return obj;
                });
    }

}

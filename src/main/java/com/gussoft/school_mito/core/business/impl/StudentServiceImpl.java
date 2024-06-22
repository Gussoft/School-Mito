package com.gussoft.school_mito.core.business.impl;

import com.gussoft.school_mito.core.business.StudentService;
import com.gussoft.school_mito.core.exception.InvalidOrderParameterException;
import com.gussoft.school_mito.core.models.Student;
import com.gussoft.school_mito.core.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl extends GenericServiceImpl<Student, String> implements StudentService {

    private final StudentRepository repository;
    @Autowired
    public StudentServiceImpl(StudentRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    protected void setId(Student request, String id) {
        request.setId(id);
    }

    @Override
    public Mono<List<Student>> getStudentsSortedByAge(String order) {
        if (order.equalsIgnoreCase("asc")) {
            return repository.findAllByOrderByAgeAsc().collectList();
        } else if (order.equalsIgnoreCase("desc")) {
            return repository.findAllByOrderByAgeDesc().collectList();
        }
        return Mono.error(new InvalidOrderParameterException("Invalid order parameter. Use 'asc' or 'desc'."));
    }
}

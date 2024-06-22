package com.gussoft.school_mito.core.repository;

import com.gussoft.school_mito.core.models.Student;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface StudentRepository extends GenericRepository<Student, String> {

    Flux<Student> findAllByOrderByAgeAsc();
    Flux<Student> findAllByOrderByAgeDesc();

}

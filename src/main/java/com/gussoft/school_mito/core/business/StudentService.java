package com.gussoft.school_mito.core.business;

import com.gussoft.school_mito.core.models.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface StudentService extends GenericService<Student, String> {

    Mono<List<Student>> getStudentsSortedByAge(String order);

}

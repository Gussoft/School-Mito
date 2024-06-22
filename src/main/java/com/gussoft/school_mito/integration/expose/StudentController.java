package com.gussoft.school_mito.integration.expose;

import com.gussoft.school_mito.core.business.GenericService;
import com.gussoft.school_mito.core.business.StudentService;
import com.gussoft.school_mito.core.models.Student;
import com.gussoft.school_mito.integration.transfer.response.GenericResponse;
import com.gussoft.school_mito.integration.transfer.response.RegistrationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin("*")
@RequestMapping(path = "/students")
public class StudentController extends GenericController<Student, String> {

    private final StudentService studentService;

    public StudentController(GenericService<Student, String> service,
                             StudentService studentService) {
        super(service);
        this.studentService = studentService;
    }

    @Override
    protected String getIdEntity(Student data) {
        return data.getId();
    }

    @GetMapping("/order")
    public Mono<ResponseEntity<GenericResponse>> findStudent(@RequestParam String order) {
        Mono<List<Student>> data = studentService.getStudentsSortedByAge(order);
        return data.flatMap(student ->
                            Mono.just(ResponseEntity.ok(
                                    new GenericResponse("OK", student))))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new GenericResponse("Error"))));
    }

}

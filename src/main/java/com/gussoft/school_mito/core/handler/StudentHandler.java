package com.gussoft.school_mito.core.handler;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import com.gussoft.school_mito.core.business.StudentService;
import com.gussoft.school_mito.core.models.Student;
import com.gussoft.school_mito.core.validator.RequestValidator;
import com.gussoft.school_mito.integration.transfer.request.StudentRequest;
import com.gussoft.school_mito.integration.transfer.response.StudentResponse;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class StudentHandler {

    private final StudentService service;
    private final ModelMapper mapper;
    private final RequestValidator validator;

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll().map(this::responseToEntity), Student.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        String id = request.pathVariable("id");

        return service.findById(id)
                .map(this::responseToEntity)
                .flatMap(e -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(e))
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<StudentRequest> mono = request.bodyToMono(StudentRequest.class);
        return mono
                .flatMap(validator::validate)
                .flatMap(Client ->
                        service.save(entityToRequest(Client)))
                .map(this::responseToEntity)
                .flatMap(e -> ServerResponse
                        .created(URI.create(request.uri().toString().concat("/").concat(e.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(e)));
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        String id = request.pathVariable("id");

        return request.bodyToMono(StudentRequest.class)
                .map(e -> {
                    e.setId(id);
                    return e;
                })
                .flatMap(validator::validate)
                .flatMap(e -> service.update(id, entityToRequest(e)))
                .map(this::responseToEntity)
                .flatMap(e -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(e))
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");
        return service.delete(id)
                .flatMap(result -> {
                    if (result) {
                        return ServerResponse.noContent().build();
                    } else {
                        return ServerResponse.notFound().build();
                    }
                });
    }


    private StudentResponse responseToEntity(Student obj) {
        return mapper.map(obj, StudentResponse.class);
    }

    private Student entityToRequest(StudentRequest obj) {
        return mapper.map(obj, Student.class);
    }

}

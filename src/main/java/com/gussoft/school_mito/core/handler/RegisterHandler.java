package com.gussoft.school_mito.core.handler;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import com.gussoft.school_mito.core.business.RegistrationService;
import com.gussoft.school_mito.core.models.Course;
import com.gussoft.school_mito.core.models.Registration;
import com.gussoft.school_mito.core.validator.RequestValidator;
import com.gussoft.school_mito.integration.transfer.request.RegistrationRequest;
import com.gussoft.school_mito.integration.transfer.response.RegistrationResponse;
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
public class RegisterHandler {

    private final RegistrationService service;
    private final ModelMapper mapper;
    private final RequestValidator validator;

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll().map(this::responseToEntity), Course.class);
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
        Mono<RegistrationRequest> mono = request.bodyToMono(RegistrationRequest.class);
        return mono
                .flatMap(validator::validate)
                .flatMap(data ->
                        service.save(entityToRequest(data)))
                .map(this::responseToEntity)
                .flatMap(e -> ServerResponse
                        .created(URI.create(request.uri().toString().concat("/").concat(e.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(e)));
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        String id = request.pathVariable("id");

        return request.bodyToMono(RegistrationRequest.class)
                .map(e -> {
                    e.setId(id);
                    return e;
                })
                .flatMap(validator::validate)
                .flatMap(data -> service.update(id, entityToRequest(data)))
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


    private RegistrationResponse responseToEntity(Registration obj) {
        return mapper.map(obj, RegistrationResponse.class);
    }

    private Registration entityToRequest(RegistrationRequest obj) {
        return mapper.map(obj, Registration.class);
    }
    
}

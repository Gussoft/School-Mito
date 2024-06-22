package com.gussoft.school_mito.integration.expose;

import com.gussoft.school_mito.core.business.GenericService;
import com.gussoft.school_mito.core.business.RegistrationService;
import com.gussoft.school_mito.core.models.Course;
import com.gussoft.school_mito.core.models.Registration;
import com.gussoft.school_mito.integration.transfer.request.RegistrationRequest;
import com.gussoft.school_mito.integration.transfer.response.GenericResponse;
import com.gussoft.school_mito.integration.transfer.response.RegistrationResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@Slf4j
@CrossOrigin("*")
@RequestMapping(path = "/registers")
public class RegisterController {

    private final RegistrationService registrationService;
    private final ModelMapper mapper;

    public RegisterController(RegistrationService registrationService, ModelMapper mapper) {
        this.registrationService = registrationService;
        this.mapper = mapper;
    }

    @PostMapping
    public Mono<ResponseEntity<RegistrationResponse>> saveRegistration(
            @Valid @RequestBody RegistrationRequest request, final ServerHttpRequest req) {
        request.setDate(LocalDateTime.now());
        return registrationService.save(entityToRequest(request))
                .map(this::responseToEntity)
                .map(data -> ResponseEntity.created(
                                URI.create(req.getURI().toString() + "/" + data.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(data))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<RegistrationResponse>> findRegistrationById(@PathVariable("id") String id) {
        return registrationService.findByIdRegistration(id)
                .map(this::responseToEntity)
                .map(data -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(data))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    private Registration entityToRequest(RegistrationRequest obj) {
        return mapper.map(obj, Registration.class);
    }

    private RegistrationResponse responseToEntity(Registration obj) {
        return mapper.map(obj, RegistrationResponse.class);
    }

}

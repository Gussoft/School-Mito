package com.gussoft.school_mito.core.business;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GenericService<T, ID> {

    Flux<T> findAll();

    Mono<Page<T>> findAllPage(Pageable page, String name);

    Mono<T> findById(ID id);

    Mono<T> save(T request);

    Mono<T> update(ID id, T request);

    Mono<Boolean> delete(ID id);
    
}

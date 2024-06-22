package com.gussoft.school_mito.core.business.impl;

import com.gussoft.school_mito.core.business.GenericService;
import com.gussoft.school_mito.core.repository.GenericRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public abstract class GenericServiceImpl<T, ID> implements GenericService<T, ID> {

    private final GenericRepository<T, ID> repository;

    protected abstract void setId(T request, ID id);

    @Override
    public Flux<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Page<T>> findAllPage(Pageable page, String name) {
        return repository.findByNameContainingIgnoreCase(name)
                .skip((long) page.getPageNumber() * page.getPageSize())
                .take(page.getPageSize())
                .collectList()
                .zipWith(repository.count())
                .map(tuple -> new PageImpl<>(tuple.getT1(), page, tuple.getT2()));
    }

    @Override
    public Mono<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Mono<T> save(T request) {
        return repository.save(request);
    }

    @Override
    @Transactional
    public Mono<T> update(ID id, T request) {
        return repository.findById(id)
                .flatMap(data -> {
                    setId(request, id);
                    return repository.save(request);
                })
                .switchIfEmpty(Mono.error(new RuntimeException("Entity not found")));
    }

    @Override
    @Transactional
    public Mono<Boolean> delete(ID id) {
        return repository.findById(id)
                .hasElement()
                .flatMap(data -> {
                    if (data) {
                        return repository.deleteById(id).thenReturn(true);
                    } else {
                        return Mono.just(false);
                    }
                });
    }
}

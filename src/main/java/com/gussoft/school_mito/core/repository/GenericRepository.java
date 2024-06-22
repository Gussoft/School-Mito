package com.gussoft.school_mito.core.repository;

import com.gussoft.school_mito.core.models.Student;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import reactor.core.publisher.Flux;

@NoRepositoryBean
public interface GenericRepository<T, ID> extends ReactiveMongoRepository<T,ID> {

    //siempre el campo a buscar debe ser llamarse name
    @Query("{ 'name': { $regex: ?0, $options: 'i' } }")
    Flux<T> findByNameContainingIgnoreCase(String name);

}

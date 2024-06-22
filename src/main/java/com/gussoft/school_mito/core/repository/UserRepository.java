package com.gussoft.school_mito.core.repository;

import com.gussoft.school_mito.core.models.User;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends GenericRepository<User, String> {

    Mono<User> findOneByUsername(String username);

}

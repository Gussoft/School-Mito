package com.gussoft.school_mito.core.repository;

import com.gussoft.school_mito.core.models.Roles;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface RoleRepository extends GenericRepository<Roles, String> {

    Mono<Roles> findByName(String name);

}

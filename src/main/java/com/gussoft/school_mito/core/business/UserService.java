package com.gussoft.school_mito.core.business;

import com.gussoft.school_mito.core.models.User;
import com.gussoft.school_mito.core.security.UserDetail;
import reactor.core.publisher.Mono;

public interface UserService extends GenericService<User, String> {

    Mono<User> saveHash(User user);

    Mono<UserDetail> searchByUser(String username);

}

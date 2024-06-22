package com.gussoft.school_mito.core.business.impl;

import com.gussoft.school_mito.core.business.UserService;
import com.gussoft.school_mito.core.models.Roles;
import com.gussoft.school_mito.core.models.User;
import com.gussoft.school_mito.core.repository.RoleRepository;
import com.gussoft.school_mito.core.repository.UserRepository;
import com.gussoft.school_mito.core.security.UserDetail;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class UserServiceImpl extends GenericServiceImpl<User, String> implements UserService {

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, RoleRepository roleRepository, BCryptPasswordEncoder encoder) {
        super(repository);
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @Override
    protected void setId(User request, String id) {
        request.setId(id);
    }

    @Override
    public Mono<User> saveHash(User user) {
        return roleRepository.findByName("USUARIO")
                        .flatMap(roles -> {
                            user.setRoles(List.of(roles));
                            user.setPassword(encoder.encode(user.getPassword()));
                            return repository.save(user);
                        });
    }

    @Override
    public Mono<UserDetail> searchByUser(String username) {
        return repository.findOneByUsername(username)
                .flatMap(users -> Flux.fromIterable(users.getRoles())
                        .flatMap(userRole -> roleRepository.findById(userRole.getId())
                                .map(Roles::getName))
                        .collectList()
                        .map(roles -> new UserDetail(
                                users.getUsername(), users.getPassword(), users.getStatus(), roles))
                );
    }


}

package com.gussoft.school_mito.core.business;

import com.gussoft.school_mito.core.models.Registration;
import reactor.core.publisher.Mono;

public interface RegistrationService extends GenericService<Registration, String> {

    Mono<Registration> findByIdRegistration(String id);

}

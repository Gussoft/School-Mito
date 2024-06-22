package com.gussoft.school_mito.integration.expose;

import com.gussoft.school_mito.core.business.GenericService;
import com.gussoft.school_mito.core.business.RegistrationService;
import com.gussoft.school_mito.core.models.Registration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin("*")
@RequestMapping(path = "/v3/registers")
public class RegisterGController extends GenericController<Registration, String> {

    private final RegistrationService registrationService;

    public RegisterGController(GenericService<Registration, String> service,
                               RegistrationService registrationService) {
        super(service);
        this.registrationService = registrationService;
    }

    @Override
    protected String getIdEntity(Registration data) {
        return data.getId();
    }

}

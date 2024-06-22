package com.gussoft.school_mito.integration.expose;

import com.gussoft.school_mito.core.business.GenericService;
import com.gussoft.school_mito.core.models.Roles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin("*")
@RequestMapping(path = "/roles")
public class RoleController extends GenericController<Roles, String> {

    public RoleController(GenericService<Roles, String> service) {
        super(service);
    }

    @Override
    protected String getIdEntity(Roles data) {
        return data.getId();
    }

}

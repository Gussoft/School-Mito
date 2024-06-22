package com.gussoft.school_mito.core.business.impl;

import com.gussoft.school_mito.core.business.RoleService;
import com.gussoft.school_mito.core.models.Roles;
import com.gussoft.school_mito.core.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoleServiceImpl extends GenericServiceImpl<Roles, String> implements RoleService {

    @Autowired
    public RoleServiceImpl(RoleRepository repository) {
        super(repository);
    }

    @Override
    protected void setId(Roles request, String id) {
        request.setId(id);
    }

}

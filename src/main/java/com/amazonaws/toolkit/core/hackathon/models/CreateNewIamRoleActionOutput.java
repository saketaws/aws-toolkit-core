package com.amazonaws.toolkit.core.hackathon.models;

import com.amazonaws.services.identitymanagement.model.Role;

public class CreateNewIamRoleActionOutput extends ActionOutput {
    private final Role createdRole;

    public CreateNewIamRoleActionOutput(ActionResult result, Role createdRole) {
        super(result);
        this.createdRole = createdRole;
    }

    public Role getCreatedRole() {
        return createdRole;
    }
}

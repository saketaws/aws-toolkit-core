package com.amazonaws.toolkit.core.hackathon.models;

public class CreateNewIamRoleInput extends ActionInput {
    private final String roleName;
    private final String policyName;
    // What users can specify the Role
    private final String assumeRolePolicy;
    // The resources the users can access and what operations they can perform ont hem
    private final String rolePolicy;

    public CreateNewIamRoleInput(PredefinedRole predefinedRole) {
        this.roleName = predefinedRole.getRoleName();
        this.policyName = predefinedRole.getPolicyName();
        this.assumeRolePolicy = predefinedRole.getAssumeRolePolicy();
        this.rolePolicy = predefinedRole.getRolePolicy();
    }

    // For creating custom roles.
    public CreateNewIamRoleInput(String roleName, String policyName, String assumeRolePolicy, String rolePolicy) {
        this.roleName = roleName;
        this.policyName = policyName;
        this.assumeRolePolicy = assumeRolePolicy;
        this.rolePolicy = rolePolicy;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getPolicyName() {
        return policyName;
    }

    public String getAssumeRolePolicy() {
        return assumeRolePolicy;
    }

    public String getRolePolicy() {
        return rolePolicy;
    }
}

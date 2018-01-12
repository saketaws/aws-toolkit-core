package com.amazonaws.toolkit.core.hackathon.models;

public class CreateNewIamRoleInput extends ActionInput {
    private final AwsScope awsScope;
    private final String roleName;
    private final String policyName;
    // What users can specify the Role
    private final String assumeRolePolicy;
    // The resources the users can access and what operations they can perform ont hem
    private final String rolePolicy;

    // Using a PredefinedRole to fill out policy data.
    public CreateNewIamRoleInput(PredefinedRole predefinedRole, AwsScope awsScope) {
        this(predefinedRole.getRoleName(), predefinedRole.getPolicyName(),
                predefinedRole.getAssumeRolePolicy(), predefinedRole.getRolePolicy(), awsScope);
    }

    // For creating custom roles.
    public CreateNewIamRoleInput(String roleName, String policyName, String assumeRolePolicy,
            String rolePolicy, AwsScope awsScope) {
        this.roleName = roleName;
        this.policyName = policyName;
        this.assumeRolePolicy = assumeRolePolicy;
        this.rolePolicy = rolePolicy;
        this.awsScope = awsScope;
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

    public AwsScope getAwsScope() {
        return awsScope;
    }
}

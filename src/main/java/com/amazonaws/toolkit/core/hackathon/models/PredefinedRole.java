package com.amazonaws.toolkit.core.hackathon.models;

import java.util.UUID;

public enum PredefinedRole {
    BASIC_LAMBDA_EXECUTION_ROLE("basic_lambda_execution_role", getRandomPolicyName(), "Basic Lambda Execution Role",
            Constants.LAMBDA_ASSUME_ROLE_POLICY, Constants.BASIC_LAMBDA_EXECUTION_ROLE_POLICY);

    private final String roleName;
    private final String policyName;
    private final String description;
    // What users can specify the Role
    private final String assumeRolePolicy;
    // The resources the users can access and what operations they can perform ont hem
    private final String rolePolicy;

    private PredefinedRole(String roleName, String policyName, String description, String assumeRolePolicy, String rolePolicy) {
        this.roleName = roleName;
        this.policyName = policyName;
        this.description = description;
        this.assumeRolePolicy = assumeRolePolicy;
        this.rolePolicy = rolePolicy;
    }

    private static class Constants {
        private static final String BASIC_LAMBDA_EXECUTION_ROLE_POLICY =
                "{" +
                    "\"Version\": \"2012-10-17\"," +
                    "\"Statement\": [" +
                        "{" +
                            "\"Effect\": \"Allow\"," +
                            "\"Action\": [" +
                                "\"logs:*\"" +
                            "]," +
                            "\"Resource\": \"arn:aws:logs:*:*:*\"" +
                        "}" +
                    "]" +
                "}";

        private static final String LAMBDA_ASSUME_ROLE_POLICY =
                "{" +
                    "\"Version\": \"2012-10-17\"," +
                    "\"Statement\": [" +
                        "{" +
                            "\"Sid\": \"\"," +
                            "\"Effect\": \"Allow\"," +
                            "\"Principal\": {" +
                                "\"Service\": \"lambda.amazonaws.com\"" +
                            "}," +
                            "\"Action\": \"sts:AssumeRole\"" +
                        "}" +
                    "]" +
                "}";
    }

    public String getRoleName() {
        return roleName;
    }

    public String getDescription() {
        return description;
    }

    public String getAssumeRolePolicy() {
        return assumeRolePolicy;
    }

    public String getRolePolicy() {
        return rolePolicy;
    }

    public String getPolicyName() {
        return policyName;
    }

    private static String getRandomPolicyName() {
        return "role_policy_"
                + UUID.randomUUID().toString();
    }
}

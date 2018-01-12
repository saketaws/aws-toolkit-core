package com.amazonaws.toolkit.core.hackathon.actions;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClient;
import com.amazonaws.services.identitymanagement.model.AttachRolePolicyRequest;
import com.amazonaws.services.identitymanagement.model.CreatePolicyRequest;
import com.amazonaws.services.identitymanagement.model.CreateRoleRequest;
import com.amazonaws.services.identitymanagement.model.CreateRoleResult;
import com.amazonaws.services.identitymanagement.model.Role;
import com.amazonaws.toolkit.core.hackathon.ActionContext;
import com.amazonaws.toolkit.core.hackathon.ActionInfo;
import com.amazonaws.toolkit.core.hackathon.models.ActionException;
import com.amazonaws.toolkit.core.hackathon.models.ActionOutput.ActionResult;
import com.amazonaws.toolkit.core.hackathon.models.CreateNewIamRoleActionOutput;
import com.amazonaws.toolkit.core.hackathon.models.CreateNewIamRoleInput;

public class CreateNewIamRoleAction extends BaseAction<CreateNewIamRoleInput, CreateNewIamRoleActionOutput, ActionException>{

    public CreateNewIamRoleAction() {
        super(ActionInfo.CREATE_NEW_IAM_ROLE);
    }

    @Override
    protected CreateNewIamRoleActionOutput doExecute(CreateNewIamRoleInput input, ActionContext context) throws ActionException {
        final AmazonIdentityManagement iam = AmazonIdentityManagementClient.builder()
                .withCredentials(new ProfileCredentialsProvider(input.getAwsScope().getProfile()))
                .withRegion(input.getAwsScope().getRegion())
                .build();

        CreateRoleResult result = iam.createRole(new CreateRoleRequest().withRoleName(input.getRoleName())
                .withAssumeRolePolicyDocument(input.getAssumeRolePolicy()));
        Role createdRole = result.getRole();
        context.getLogger().info("Created new IAM Role. Arn %s", createdRole.getArn());
        String policyArn = iam.createPolicy(
                new CreatePolicyRequest().withPolicyName(input.getPolicyName()).withPolicyDocument(input.getRolePolicy()))
                .getPolicy().getArn();
        iam.attachRolePolicy(new AttachRolePolicyRequest()
                .withRoleName(input.getRoleName())
                .withPolicyArn(policyArn));
        context.getLogger().info("Created new Policy. Arn %s", policyArn);
        return new CreateNewIamRoleActionOutput(ActionResult.SUCCEEDED, createdRole);
    }
}

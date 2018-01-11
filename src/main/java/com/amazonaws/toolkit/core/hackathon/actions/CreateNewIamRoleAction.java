package com.amazonaws.toolkit.core.hackathon.actions;

import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
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

    protected CreateNewIamRoleAction() {
        super(ActionInfo.CREATE_NEW_IAM_ROLE);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected CreateNewIamRoleActionOutput doExecute(CreateNewIamRoleInput input, ActionContext context) throws ActionException {
        final AmazonIdentityManagement iam = AmazonIdentityManagementClientBuilder.defaultClient();
        CreateRoleResult result = iam.createRole(new CreateRoleRequest().withRoleName(input.getRoleName())
                .withAssumeRolePolicyDocument(input.getAssumeRolePolicy()));
        Role createdRole = result.getRole();
        String policyArn = iam.createPolicy(
                new CreatePolicyRequest().withPolicyName(input.getPolicyName()).withPolicyDocument(input.getRolePolicy()))
                .getPolicy().getArn();
        iam.attachRolePolicy(new AttachRolePolicyRequest()
                .withRoleName(input.getRoleName())
                .withPolicyArn(policyArn));
        return new CreateNewIamRoleActionOutput(ActionResult.SUCCEEDED, createdRole);
    }
}

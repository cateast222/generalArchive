package com.ebs.platform.business.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoleByAddUserRequest {

    private String roleId;

    private List<String> userIds;
}

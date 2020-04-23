package com.ebs.platform.business.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserByAddRoleRequest {

    private String userId;

    private List<String> roels;
}

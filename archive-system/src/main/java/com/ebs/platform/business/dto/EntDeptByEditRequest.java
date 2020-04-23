package com.ebs.platform.business.dto;

import lombok.Data;

@Data
public class EntDeptByEditRequest {

    private String id;

    private String name;

    private String parentId;
}

package com.ebs.platform.business.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TenantAddRequest {

    private String appId;

    private String entId;

    private String userName;

    private Date endDate;
}

package com.ebs.platform.business.dto.user;

import lombok.Data;

@Data
public class UserAddRequest {

    private String userName;

    private String password;

    private String personnelId;
}

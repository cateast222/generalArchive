package com.ebs.platform.business.dto.personnel;

import com.ebs.platform.core.enums.SexEnum;
import lombok.Data;

@Data
public class PersonnelByEditRequest {

    private String id;

    private String name;

    private String email;

    private SexEnum sex;

    private String tel;

    private String deptId;
}

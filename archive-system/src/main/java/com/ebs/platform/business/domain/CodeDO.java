package com.ebs.platform.business.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 何老师PB开发的 干部人事档案系统表对应实体，暂未使用
 */
@Entity(name = "code")
@Data
public class CodeDO {

    @Id
    private String id;

    private String parent;

    private String dmh;

    private String dm;

    private String cc;

    private String zhon;

    private String zhonJc;

    private String cyz;

    private String pysy;


}

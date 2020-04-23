package com.ebs.platform.business.domain.oldEntity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 档案目录表
 */
@Data
@Entity
public class Damlb {

    private String XLH;//序列号

    @Id
    private String DAH;//档案号

    private String CLFLBM;//材料分类表

    private String CLMC;//材料名称

    private String FS;//

    private String NI;//年

    private String YUE;//月

    private String YS;//

    private String RI;//日

    private String BZ;//备注

    private String Flag;
}

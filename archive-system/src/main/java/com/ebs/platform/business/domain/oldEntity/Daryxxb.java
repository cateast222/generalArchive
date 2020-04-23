package com.ebs.platform.business.domain.oldEntity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 档案人员信息表
 */
@Data
@Entity
public class Daryxxb {

    @Id
    private String DAH;//档案号 相当于主键，档案目录表以此关联

    private String FLBM;// 分类编码

    private String XM;//  姓名

    private String DWBM;//    单位编码

    private String ZWBM;//    职务编码

    private String DWMC;//    单位名称

    private String JGBM;//    籍贯编码

    private String CSRQ;//    出生年月

    private String Zwmc;//    职务名称

    private String BZ;//  备注

    private String PYSY;//    拼音缩写

    private String JGMC;//    籍贯名称

    private String XH;//  箱号

    private String XB;//  性别

    private String PERS_CODE;//

    private String BH;//

    private String A0214;//   内设机构

    private String Dazlzt; // 档案转来状态

    private String ZBS;// 正本卷数

    private String FBS;// 副本卷数

    private String A0194;//   内设机构（名称）

    private String DAZT;//    档案状态（value）

    private String DAZRSJ;//  档案转入时间

    private String ZRDWMC;//  转入单位名称

    private String DAZCSJ;//  档案转出时间

    private String ZCDWMC;//  转出单位名称

}

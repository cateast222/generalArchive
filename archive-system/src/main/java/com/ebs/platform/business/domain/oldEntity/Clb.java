package com.ebs.platform.business.domain.oldEntity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * clb 材料表 就是材料模板
 */

@Data
@Entity
public class Clb {
    /*
CLFLBM  材料分类编码
CLBM    材料编码
CLMC    材料名称
CY  常用
Pysy    拼音缩写
Xh  序号
     */
    private String CLFLBM;

    @Id
    private String CLBM;

    private String CLMC;

    private String CY;

    private String Pysy;

    private String Xh;
}

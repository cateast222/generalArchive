package com.ebs.platform.business.domain;

import com.ebs.platform.core.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;

/**
 * 行政区划 籍贯，单独弄一张表
 *
 */
@Data
@Entity(name = "tb_native_place")
public class NativePlaceDO extends BaseEntity {

    private String name;

    private String code;

    private String parentId;
    /**
     * 1省 2市 3区/县
     */
    private Integer theRank;

    @ApiModelProperty(value = "拼音缩写")
    private String pinYin;
}

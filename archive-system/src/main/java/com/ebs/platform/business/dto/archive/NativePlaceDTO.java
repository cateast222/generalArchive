package com.ebs.platform.business.dto.archive;

import com.ebs.platform.core.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import java.util.List;

/**
 * 行政区划 籍贯，单独弄一张表
 *
 */
@Data
public class NativePlaceDTO extends BaseEntity {

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "代码")
    private String code;

    @ApiModelProperty(value = "父ID")
    private String parentId;

    @ApiModelProperty(value = "层次 1省2市3区/县")
    private Integer theRank;

    @ApiModelProperty(value = "拼音缩写")
    private String pinYin;

    List<NativePlaceDTO> children;
}

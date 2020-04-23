package com.ebs.platform.business.dto.archive;

import com.ebs.platform.core.query.IMetadataObject;
import com.ebs.platform.core.query.MetadataField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 * Created by liujie on 2019/11/4.
 */
@Data
public class EntityTypeDTO implements IMetadataObject {

    private Integer id;

    /** 实体分类名称
     *
     * @pdOid f0c11e5f-d31e-41ec-9e21-11b6594066bf */
    @ApiModelProperty(value = "实体分类名称")
    @MetadataField(name = "实体分类名称")
    private String entityName;
    /** 实体分类编号
     *
     * @pdOid fe903f79-ecad-43f6-8b6d-703d6dc842e5 */
    @ApiModelProperty(value = "实体分类编号")
    @MetadataField(name = "实体分类编号")
    private String entityCode;
    /** 父实体类编号
     *
     * @pdOid f39fea1c-1c56-431b-ac27-cffbd0d3fc4c */
    @ApiModelProperty(value = "父实体类编号")
    @MetadataField(name = "父实体类编号")
    private String parentCode;
    /** 排序号
     *
     * @pdOid e5f5e21b-9958-44c6-ba7f-7c2f998773ef */
    @ApiModelProperty(value = "排序号")
    @MetadataField(name = "排序号")
    private Integer entityOrder;
    /** 描述
     *
     * @pdOid 3b1e0dbf-75ca-404d-b6e7-688f6adb650c */
    @ApiModelProperty(value = "描述")
    @MetadataField(name = "描述")
    private String entityDesc;
    /** 关联全宗号
     *
     * @pdOid d50c1c33-c09d-4707-abed-33ebf47cbe8f */
    @ApiModelProperty(value = "关联全宗号")
    @MetadataField(name = "关联全宗号")
    private String entityFonds;

    private List<Object> children;
}

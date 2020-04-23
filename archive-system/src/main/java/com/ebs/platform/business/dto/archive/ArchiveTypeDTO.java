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
public class ArchiveTypeDTO implements IMetadataObject {

    private Integer id;

    @ApiModelProperty(value = "档案门类名称")
    @MetadataField(name = "类型名称")
    private String typeName;

    private String title;

    private Integer typeOrder;
    private Integer parentCode;
    private String parentName;

    @ApiModelProperty(value = "文档目录层数")
    @MetadataField(name = "文档目录层数")
    private Integer typeLayer;

    @ApiModelProperty(value = "类型描述")
    @MetadataField(name = "类型描述")
    private String typeDesc;

    private List<Object> children;

}

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
public class ArchiveTypeParentDTO implements IMetadataObject {

    @ApiModelProperty(value = "档案门类名称")
    @MetadataField(name = "类型名称")
    private String typeName;

    private Integer parentCode;
    private String parentName;

    private Integer id;
    private String label;

    private List<Object> children;

}

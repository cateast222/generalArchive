package com.ebs.platform.business.dto.archive;

import com.ebs.platform.core.query.IMetadataObject;
import com.ebs.platform.core.query.MetadataField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 * Created by liujie on 2019/11/16.
 */
@Data
public class EntityTypeNodeDTO implements IMetadataObject {

    private Integer id;
    private String label;
    private String parentId;

    private List<Object> children;
}

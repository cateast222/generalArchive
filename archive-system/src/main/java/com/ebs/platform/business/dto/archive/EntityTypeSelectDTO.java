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
public class EntityTypeSelectDTO implements IMetadataObject {

    private Integer value;

    /** 实体分类名称
     *
     * @pdOid f0c11e5f-d31e-41ec-9e21-11b6594066bf */
    private String title;

    private String parentId;

    private List<Object> children;
}

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
public class ArchiveTypeRequestParamDTO implements IMetadataObject {

    private Integer typeLayer;


}

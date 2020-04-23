package com.ebs.platform.business.dto.archive;

import com.ebs.platform.core.dto.AppDictValueDTO;
import com.ebs.platform.core.query.IMetadataObject;
import lombok.Data;

import java.util.List;

/**
 * Created by liujie on 2019/11/28.
 */
@Data
public class TableConfigParamDTO implements IMetadataObject {


    private Integer typeCode;
    private Integer typeLevel;

}

package com.ebs.platform.core.query;

import com.ebs.platform.core.enums.ResourceData;
import com.ebs.platform.core.enums.DataTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author liubo   18855815@qq.com
 * @date 2018-09-10
 * @desc
 */
@Data
public class MetadataFieldDTO implements Serializable{

    private String name;

    private String displayName;

    private DataTypeEnum dataType;

    private int isArray;

    private boolean isColumn;

    private boolean isFilter;

    private boolean isFilterHidden;

    private boolean isKey;

    private List<MetadataFieldDTO> childField;

    private List<ResourceData> selectData;
}

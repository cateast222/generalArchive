package com.ebs.platform.business.dto.archive;

import com.ebs.platform.core.dto.AppDictValueDTO;
import com.ebs.platform.core.enums.DataTypeEnum;
import com.ebs.platform.core.enums.ResourceData;
import com.ebs.platform.core.query.IMetadataObject;
import com.ebs.platform.core.query.MetadataFieldDTO;
import lombok.Data;

import java.util.List;

/**
 * Created by liujie on 2019/11/7.
 */
@Data
public class TableConfigAddOrUpdateFormDTO implements IMetadataObject {

    /**
     * columnCode
     */
    private String name;

    /**
     * columnName
     */
    private String displayName;
    /**
     * columnTypecode
     */
    private DataTypeEnum dataType;

    private int isArray = 0;

    private boolean isColumn = true;

    private boolean isFilter = true;

    private boolean isFilterHidden = false;

    private boolean isKey = false;

    private List<MetadataFieldDTO> childField;

    /**
     * dict
     */
    private List<ResourceData> selectData;

    /**
     * 最小长度
     */
    private Integer minLength;
    /**
     * 最大长度
     */
    private Integer columnLength;
    /**
     * 是否唯一
     */
    private Integer isPrimary;
    /**
     * 是否必填
     */
    private Integer isRequired;
    /**
     * 组件类型
     */
    private String columnComponent;
    /**
     * 数据限制
     */
    private String columnLimit;
    /**
     * 默认值
     */
    private String defaultValue;
    /**
     * 编辑序号
     */
    private Integer editOrder;

}

package com.ebs.platform.business.dto.archive;

import com.ebs.platform.core.dto.AppDictValueDTO;
import com.ebs.platform.core.query.IMetadataObject;
import lombok.Data;

import java.util.List;

/**
 * Created by liujie on 2019/11/7.
 */
@Data
public class TableConfigDTO implements IMetadataObject {

    private Integer id;

    private String columnName;
    private String columnCode;
    private String columnType;
    private String columnTypecode;
    private Integer typeCode;
    private Integer typeLevel;
    private Integer minLength;
    private Integer columnLength;
    private Integer isPrimary;
    private Integer isForeign;
    private Integer isRequired;
    private String columnComponent;
    private String columnDatasource;
    private String columnDict;
    private String cascadeData;
    private String columnLimit;
    private String defaultValue;
    private Integer queryShow;
    private Integer listShow;
    private Integer editShow;
    private Integer editOrder;
    private Integer queryOrder;
    private Integer listOrder;
    private String archiveType;

    private List<AppDictValueDTO> dict;

}

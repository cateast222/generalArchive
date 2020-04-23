package com.ebs.platform.business.dto.fonds;


import com.ebs.platform.core.query.IMetadataObject;
import com.ebs.platform.core.query.MetadataField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FondsDTO  implements IMetadataObject {

    @ApiModelProperty(value = "全宗号",name = "code")
    @MetadataField(name = "全宗号")
    private String code;

    @ApiModelProperty(value = "名称",name = "name")
    @MetadataField(name = "名称")
    private String name;

}

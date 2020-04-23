package com.ebs.platform.business.dto.archivetext;


import com.ebs.platform.core.query.MetadataField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ArchiveTextDTO {

    @ApiModelProperty(value = "ID号",name = "id")
    private Integer Id;
    @MetadataField(name = "题名")
    @ApiModelProperty(value = "题名",name = "title")
    private String title;
    @MetadataField(name = "档号")
    @ApiModelProperty(value = "档号",name = "archive_code")
    private String archiveCode;
    @MetadataField(name = "档案类别")
    @ApiModelProperty(value = "档案类别",name = "type_name")
    private String archiveType;
    @MetadataField(name = "档案内容")
    @ApiModelProperty(value = "档案内容",name = "archive_text")
    private String archiveText;

    @ApiModelProperty(value = "档案编号",name = "archive_id")
    private  Integer archiveId;

}

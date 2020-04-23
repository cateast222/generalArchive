package com.ebs.platform.business.dto.archive;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 档案室
 * @author lwy
 * @Date 2019-06-06 11:13
 */
@Data
public class ArchiveRoomDTO {

    @ApiModelProperty(value ="主键")
    private Integer id;

    @ApiModelProperty(value ="名称")
    private String name;

    private String title;

    public String getTitle() {
        return name;
    }

    @ApiModelProperty(value ="级别")
    private Integer theRank;

    @ApiModelProperty(value ="父类ID")
    private Integer parentId;

    @ApiModelProperty(value ="柜子的行")
    private String theRow;

    @ApiModelProperty(value ="柜子的列")
    private String theColumn;

    @ApiModelProperty(value ="备注")
    private String remark;

    @ApiModelProperty(value ="容量")
    private Integer amount;
    /**
     * 子集
     */
    private List<ArchiveRoomDTO> children;

}

package com.ebs.platform.business.dto.archive;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 档案上下架查询档案时的参数
 * @author liujie
 * @Date 2019-11-30
 */
@Data
public class ArchiveRoomParamDTO {

    /**
     * 柜子id
     */
    private Integer id;

    /**
     * 柜子的行
     */
    private String theRow;

    /**
     * 柜子的列
     */
    private String theColumn;

    /**
     * 档案类别
     */
    private Integer archiveType;

    /**
     * 档号
     */
    private String archiveCode;

    /**
     * 柜子id
     */
    private Integer archiveCabinetId ;

}

package com.ebs.platform.business.domain;

import com.ebs.platform.core.BaseEntity;
import com.ebs.platform.core.query.IMetadataObject;
import com.ebs.platform.core.query.MetadataField;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Entity(name = "sys_log")
@Data
public class SysLog extends BaseEntity implements IMetadataObject {

    @MetadataField(name = "登录ID")
    private String username; //用户名

    @MetadataField(name = "操作")
    private String operation; //操作

    private String method; //方法名

    @Column(columnDefinition = "TEXT")
    private String params; //参数

    @MetadataField(name = "IP")
    private String ip; //ip地址

    @MetadataField(name = "时间")
    private Date createDate; //操作时间
    //创建getter和setter方法

    @Column(columnDefinition = "TEXT")
    private String resultParams;//结果参数

}

package com.ebs.platform.business.dto.archive;

import com.ebs.platform.core.query.IMetadataObject;
import com.ebs.platform.core.query.MetadataField;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * Created by liujie on 2019/11/25.
 */
@Data
public class OcrArchivesDTO implements IMetadataObject, Serializable {

    /**
     * 档案类别
     */
    @MetadataField(name = "档案类别")
    @Column(name="archive_type")
    private String archiveType;

    /**
     * 档号
     */
    @MetadataField(name = "档号")
    @Column(name="archive_code")
    private String archiveCode;

    /**
     * 提名
     */
    @MetadataField(name = "提名")
    @Column(name="title")
    private String title;

    public OcrArchivesDTO(){}

    public OcrArchivesDTO(String archiveType,String archiveCode,String title){
        this.archiveType = archiveType;
        this.archiveCode = archiveCode;
        this.title = title;
    }

}

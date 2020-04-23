package com.ebs.platform.business.dto.archive;

import lombok.Data;

@Data
public class ArchiveBorrowItemDto {

    /** 申请表ID**/
    private String borrowRegisterId;

    /** 借阅材料id**/
    private String archiveItemId;

}

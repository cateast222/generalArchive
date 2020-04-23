package com.ebs.platform.business.service;

import com.ebs.platform.business.domain.professionentity.ArchiveFile;
import com.ebs.platform.business.dto.archive.ArchiveFileDTO;
import net.sourceforge.tess4j.TesseractException;

import java.util.List;

/**
 * Created by liujie on 2019/11/21.
 * 档案原文
 */
public interface ArchiveFileService {

    /**
     * 保存档案原文
     * @param archiveFile
     * @return
     */
    Integer saveArchiveFile(ArchiveFile archiveFile);

    /**
     * 删除档案原文
     * @param id
     * @return
     */
    Integer deleteArchiveFile(Integer id);

    /**
     * 根据档案id查询文件
     * @return
     */
    List<ArchiveFile> queryAllByArchiveId(ArchiveFile archiveFile);

    /**
     * 根据文本id查询文件
     * @return
     */
    List<ArchiveFile> queryAllByTextId(ArchiveFile archiveFile);

    /**
     * 根据id查询文件
     * @return
     */
    ArchiveFile queryById(Integer id);

    List<ArchiveFile> queryByArchiveId(Integer archiveId);

    void executeOcr(ArchiveFileDTO[] urls) throws TesseractException;
}

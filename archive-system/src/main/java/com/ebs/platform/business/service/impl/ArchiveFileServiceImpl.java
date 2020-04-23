package com.ebs.platform.business.service.impl;

import com.ebs.platform.business.dao.professiondao.ArchiveFileDao;
import com.ebs.platform.business.domain.professionentity.ArchiveFile;
import com.ebs.platform.business.dto.archive.ArchiveFileDTO;
import com.ebs.platform.business.dto.archivetext.ArchiveTextSaveDTO;
import com.ebs.platform.business.service.ArchiveFileService;
import com.ebs.platform.business.service.IArchiveTextService;
import com.ebs.platform.core.util.OcrUtil;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 原文管理
 * @author liujie
 * @date 2019/11/25
 */
@Service
public class ArchiveFileServiceImpl extends BaseServiceImpl implements ArchiveFileService {

    @Autowired
    private Environment env;

    @Autowired
    private ArchiveFileDao archiveFileDao;
    @Autowired
    private IArchiveTextService archiveTextService;

    @Override
    @Transactional
    public Integer saveArchiveFile(ArchiveFile archiveFile) {
        ArchiveFile save = archiveFileDao.dynamicSave(archiveFile.getId(),archiveFile);
        return save.getId();
    }

    @Override
    @Transactional
    public Integer deleteArchiveFile(Integer id) {
        ArchiveFile one = archiveFileDao.getOne(id);
        one.setDeleted(true);
        return archiveFileDao.save(one).getId();
    }

    @Override
    public List<ArchiveFile> queryAllByArchiveId(ArchiveFile archiveFile) {
        return archiveFileDao.queryByArchiveIdAndDeleted(archiveFile.getArchiveId(),false);
    }

    @Override
    public List<ArchiveFile> queryAllByTextId(ArchiveFile archiveFile) {
        return archiveFileDao.queryByTextIdAndDeleted(archiveFile.getTextId(),false);
    }

    @Override
    public ArchiveFile queryById(Integer id) {
        return archiveFileDao.getOne(id);
    }

    @Override
    public List<ArchiveFile> queryByArchiveId(Integer archiveId) {
        List<ArchiveFile> list = archiveFileDao.findALLByDeletedAndArchiveId(false,archiveId);
        return list;
    }

    @Override
    public void executeOcr(ArchiveFileDTO[] dtos) throws TesseractException {
        if(dtos.length>0){
            for(int i =0;i<dtos.length;i++){
                ArchiveFileDTO dto = dtos[i];
                String text = OcrUtil.ocrExecute(dto.getFileUrl(), env.getProperty("ocrDataPath"));
                ArchiveTextSaveDTO saveDTO = new ArchiveTextSaveDTO();
                saveDTO.setArchiveText(text);
                saveDTO.setArchiveId(dto.getId());
                archiveTextService.save(saveDTO);
            }
        }
    }
}

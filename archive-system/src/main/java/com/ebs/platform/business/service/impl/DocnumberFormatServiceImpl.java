package com.ebs.platform.business.service.impl;

import com.ebs.platform.business.dao.professiondao.DocnumberFormatDao;
import com.ebs.platform.business.domain.professionentity.DocnumberFormat;
import com.ebs.platform.business.service.DocnumberFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DocnumberFormatServiceImpl implements DocnumberFormatService {

    @Autowired
    private DocnumberFormatDao docnumberFormatDao;

    @Override
    public Integer saveDocnumberFormat(DocnumberFormat docnumberFormat) {
        DocnumberFormat save = docnumberFormatDao.dynamicSave(docnumberFormat.getId(),docnumberFormat);
        return save.getId();
    }

    @Override
    public Integer deleteDocnumberFormat(Integer id) {
        DocnumberFormat one = docnumberFormatDao.getOne(id);
        one.setDeleted(true);
        return docnumberFormatDao.save(one).getId();
    }

    @Override
    public List<DocnumberFormat> findByArchiveId(Map<String,Integer> map){
        List<DocnumberFormat> byDeletedAndArchiveType = docnumberFormatDao.findByDeletedAndArchiveTypeAndArchiveLevel(false, map.get("archiveId"),map.get("archiveLevel"));
        return byDeletedAndArchiveType ;
    }

    @Override
    public DocnumberFormat findbyId(Integer id) {
        DocnumberFormat one = docnumberFormatDao.getOne(id);
        return one;
    }
}

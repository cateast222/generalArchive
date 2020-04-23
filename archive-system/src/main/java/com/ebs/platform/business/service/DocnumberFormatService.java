package com.ebs.platform.business.service;

import com.ebs.platform.business.domain.professionentity.DocnumberFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by liujie on 2019/11/13.
 * 档号格式
 */
public interface DocnumberFormatService {

    /**
     * 保存档案
     * @param docnumberFormat
     * @return
     */
    Integer saveDocnumberFormat(DocnumberFormat docnumberFormat);

    /**
     * 删除档案
     * @param id
     * @return
     */
    Integer deleteDocnumberFormat(Integer id);
    /**
     * 根据 archiveType 和 archiveLevel 查询 数据
     * */
    List<DocnumberFormat> findByArchiveId(Map<String,Integer> map);

    DocnumberFormat findbyId(Integer id);
}

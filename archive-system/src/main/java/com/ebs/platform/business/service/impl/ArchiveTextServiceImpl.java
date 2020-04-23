package com.ebs.platform.business.service.impl;

import com.ebs.platform.business.dao.professiondao.IArchiveTextDao;
import com.ebs.platform.business.domain.professionentity.ArchiveFile;
import com.ebs.platform.business.domain.professionentity.ArchiveText;
import com.ebs.platform.business.dto.archivetext.ArchiveSearcherTextDTO;
import com.ebs.platform.business.dto.archivetext.ArchiveTextDTO;
import com.ebs.platform.business.dto.archivetext.ArchiveTextSaveDTO;
import com.ebs.platform.business.mapper.ArchiveTextMapper;
import com.ebs.platform.business.myutil.MyUtil;
import com.ebs.platform.business.service.ArchiveFileService;
import com.ebs.platform.business.service.IArchiveTextService;
import com.ebs.platform.core.query.QueryRequest;
import com.ebs.platform.core.query.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.*;


@Service
public class ArchiveTextServiceImpl implements IArchiveTextService {
    @Autowired
    private IArchiveTextDao textDao ;

    @Autowired
    private ArchiveTextMapper textMapper;
    @Override
    public QueryResponse<List<ArchiveTextDTO>> queryArchiveText(QueryRequest<ArchiveSearcherTextDTO> text) {
        String searchText = text.getSearchText();
        List<ArchiveTextDTO> archiveTextDTOS = new ArrayList<>();
        Integer pageSize = text.getPageSize();
        Integer pageIndex = text.getPageIndex();
        ArchiveSearcherTextDTO condition = text.getCondition();
        Integer searchType = condition.getSearchType();

        if(StringUtils.isEmpty(searchText)){
            return null;
        }

        List<Map> maps = textDao.queryArchiveText("%" + searchText +"%", (pageIndex -1) * pageSize, pageSize,searchType);

        ArchiveTextDTO dto = null;

        for (Map map:maps){
            dto = new ArchiveTextDTO();
            int id = (Integer) map.get("id");
            String title =(String) map.get("title");
            String archieveCode =(String) map.get("archieveCode");
            String typeName =(String) map.get("typeName");
            String archiveText =" "+ (String) map.get("archiveText") +" ";
            String[] searchTexts = archiveText.split(searchText);
            archiveText = "" ;

            /***
             * 全文添加font标签
             * */
//            for (int i = 0 ;i < searchTexts.length -1;i++){
//                archiveText += searchTexts[i] +"<font color = 'red'>"+searchText+"</font>" ;
//            }
//            archiveText +=searchTexts[searchTexts.length -1];
            /**
             * 搜索内容前后150字添加font标签
             * */
            int fontsize = 50;

            String startText = searchTexts[0];
            String endText = searchTexts[1];

            if (startText.length() > fontsize)archiveText += startText.substring(startText.length() - fontsize);
            else archiveText += startText;
            fontsize = 150;
            archiveText +="<font color = 'red'>"+searchText+"</font>" ;
            if (endText.length() > fontsize) archiveText +=endText.substring(0,fontsize);
            else archiveText += endText;


            Integer archiveId =(Integer) map.get("archiveId");

            dto.setId(id);
            dto.setTitle(title);
            dto.setArchiveCode(archieveCode);
            dto.setArchiveType(typeName);
            dto.setArchiveText(archiveText);
            dto.setArchiveId(archiveId);

            archiveTextDTOS.add(dto);
        }
        long count = textDao.queryArchiveTextCount("%" + searchText +"%",searchType);

        Pageable pageable = PageRequest.of((pageIndex -1) * pageSize,pageSize);

        Page<ArchiveTextDTO> page = new PageImpl(archiveTextDTOS, pageable, count);
        QueryResponse queryResponse = new QueryResponse(page, archiveTextDTOS);

        return queryResponse;
    }

    @Override
    public Map<String, Object> queryByFileId(Integer fileId) {
        Map<String,Object> map = new HashMap();
        ArchiveText one = textDao.findByArchiveIdAndDeleted(fileId,false);
        if(one == null) return map;
        Integer id = one.getId();
        String archiveText = one.getArchiveText();
        map.put("id",id);
        map.put("archiveText",archiveText);
        return map;
    }

    @Override
    public void save(ArchiveTextSaveDTO saveDTO) {
        Integer id = saveDTO.getId();
        ArchiveText one ;
        if(id != null) one = textDao.getOne(id);
        else one = textMapper.to(saveDTO);
        one.setArchiveText(saveDTO.getArchiveText());
        one.setArchiveId(saveDTO.getArchiveId());
        new MyUtil().save(one);
        textDao.save(one);
    }
}

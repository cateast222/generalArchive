package com.ebs.platform.business.service.impl;

import com.ebs.platform.business.dao.IEngineDao;
import com.ebs.platform.business.domain.EngineDO;
import com.ebs.platform.business.dto.engine.EngineDTO;
import com.ebs.platform.business.dto.engine.EngineDataDTO;
import com.ebs.platform.business.mapper.EngineMapper;
import com.ebs.platform.business.service.IEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * 引擎接口实现
 * @author 496382223@qq.com 白の狐狸
 * @date 2019/1/03 9:53
 */
@Service
public class EngineServiceImpl extends BaseServiceImpl implements IEngineService {

    @Autowired
    private IEngineDao engineDao;

    @Autowired
    private EngineMapper engineMapper;

    @Override
    public EngineDTO getEngineById(String id) {
        EngineDO engineDO = engineDao.getOne(id);
        EngineDTO engineDTO = engineMapper.from(engineDO);
        return engineDTO;
    }

    @Override
    public EngineDTO getEngineByCode(String code) {
        EngineDTO engineDTO = null;
        List<EngineDO> engineDOS = engineDao.findAll((r,q,c)->{
            Path<Boolean> isDelete = r.get("deleted");
            Path<String> codePath = r.get("code");
            return c.and(c.equal(codePath,code),c.equal(isDelete,false));
        });
        if(engineDOS!= null && engineDOS.size() > 0){
            engineDTO = engineMapper.from(engineDOS.get(0));
        }
        return engineDTO;
    }

    @Override
    public void saveEngine(EngineDTO engineDTO) {
        //在同一个应用中，Code不允许重复
        List<Map> engineList = engineDao.listEnginesByAppIdAndCode(engineDTO.getAppId(),engineDTO.getCode());
        if(engineList!=null && engineList.size()>0){
            if(!engineList.get(0).get("id").equals(engineDTO.getId())){
                throw new com.ebs.platform.core.exception.BusinessException("该应用已存在编码为 "+engineDTO.getCode() + " 的模型!");
            }
        }
        EngineDO engineDO;
        if(engineDTO.getId()!= null && !"".equals(engineDTO.getAppId())){
            engineDO = engineDao.getOne(engineDTO.getId());
            engineDO.setName(engineDTO.getName());
            engineDO.setCode(engineDTO.getCode());
            engineDO.setRemark(engineDTO.getRemark());
            engineDO.setAppId(engineDTO.getAppId());
            if(engineDTO.getContent() !=null && !"".equals(engineDTO.getContent())){
                engineDO.setContent(engineDTO.getContent());
            }
        }else{
            engineDO = engineMapper.to(engineDTO);
            engineDO.setUserId(getUserContext().getUserId());
            engineDO.setCreateDate(new Date());
            engineDO.setDeleted(false);
            engineDO.setContent("");
        }
        engineDao.save(engineDO);
    }

    @Override
    public void removeEngine(String id) {
        EngineDO engineDO = engineDao.getOne(id);
        engineDO.setDeleted(true);
        engineDao.save(engineDO);
    }

    @Override
    public List<Map> listEnginesByAppId(String appId) {
        return engineDao.listEnginesByAppId(appId);
    }

    @Override
    public List<Map> listAllEngines(String appId,String code,String name){
        return engineDao.listAllEngines(appId,code,name);
    }

    @Override
    public List<Map> getSourceData(EngineDataDTO engineDataDTO) {
        /**exchange 1 and 0 char simple decrypt */
        String a = engineDataDTO.getSql().substring(0,1);
        String b = engineDataDTO.getSql().substring(1,2);
        String passSql = b+a+engineDataDTO.getSql().substring(2,engineDataDTO.getSql().length());
        byte[] bytes = Base64.getDecoder().decode(passSql);
        String sql = new String(bytes);
        return this.sqlQuery(sql);
    }
}

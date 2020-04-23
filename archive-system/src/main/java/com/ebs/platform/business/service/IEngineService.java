package com.ebs.platform.business.service;

import com.ebs.platform.business.dto.engine.EngineDTO;
import com.ebs.platform.business.dto.engine.EngineDataDTO;

import java.util.List;
import java.util.Map;
/**
 * 引擎接口
 * @author 496382223@qq.com 白の狐狸
 * @date 2019/1/03 9:53
 */
public interface IEngineService {

    /**
     * 根据id获取引擎实体（需要包含content）
     * @param id
     * @return
     */
    EngineDTO getEngineById(String id);
    /**
     * 根据Code获取引擎实体（需要包含Content）
     * @param code
     * @return
     */
    EngineDTO getEngineByCode(String code);
    /**
     * 保存一个引擎定义
     * @param engineDTO
     */
    void saveEngine(EngineDTO engineDTO);
    /**
     * 逻辑删除一个引擎定义
     * @param id
     */
    void removeEngine(String id);

    /**
     * 列出指定app下的所有定义（不包含逻辑删除的，并且在EngineDTO内容中，将content设置为空）
     * @param appId
     * @return
     */
    List<Map> listEnginesByAppId(String appId);

    /**
     * 列出所有的定义
     * @return
     */
    List<Map> listAllEngines(String appId,String code,String name);

    /**
     * 根据sql执行查询并返回结果,传输过程中的sql为des密文
     * @param engineDataDTO
     * @return
     */
    List<Map> getSourceData(EngineDataDTO engineDataDTO);
}

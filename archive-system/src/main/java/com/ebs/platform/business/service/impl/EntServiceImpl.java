package com.ebs.platform.business.service.impl;

import com.ebs.platform.business.dao.*;
import com.ebs.platform.business.domain.*;
import com.ebs.platform.business.dto.*;
import com.ebs.platform.business.dto.personnel.PersonnelByEditRequest;
import com.ebs.platform.business.mapper.EntDeptMapper;
import com.ebs.platform.business.mapper.EntMapper;
import com.ebs.platform.business.mapper.PersonnelMapper;
import com.ebs.platform.business.service.IEntService;
import com.ebs.platform.core.exception.BusinessException;
import com.ebs.platform.core.query.QueryRequest;
import com.ebs.platform.core.query.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 企业服务
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/4 10:39
 */
@Service
public class EntServiceImpl extends BaseServiceImpl implements IEntService {

    @Autowired
    private IEntDao entDao;

    @Autowired
    private EntMapper entMapper;

    @Override
    public List<Map> listAccountByDeptId(String deptId) {
        String sql = "select a.id,a.name,a.tel,a.email,b.id as accountId,b.user_name,b.password from tb_personnel a \n" +
                "join tb_user b on a.id = b.personnel_id\n" +
                "where a.dept_id = ? and a.is_deleted = 0 and b.is_deleted = 0";
        List<Map> res = this.sqlQuery(sql,new Object[]{deptId});
        return res;
    }

    @Autowired
    private IEntDeptDao entDeptDao;

    @Autowired
    private EntDeptMapper entDeptMapper;

    @Autowired
    private IPersonnelDao personnelDao;

    @Autowired
    private PersonnelMapper personnelMapper;

    @Override
    public void saveEnt(EntDTO entDTO) {
        EntDO entDO = entMapper.to(entDTO);
        entDao.save(entDO);
    }
    
    @Override
	public String saveEntForPartner(EntDTO entDTO) {
    	EntDO entDO = entMapper.to(entDTO);
        entDao.save(entDO);
		return entDO.getId();
	}

    @Override
    public void deleteEnt(String id) {
    	EntDO entDO=entDao.findById(id).get();
    	entDO.setDeleted(true);
        entDao.save(entDO);
    }

    @Override
    public QueryResponse<List<EntDTO>> listEnt(QueryRequest req) {
        Page<EntDO> result = entDao.queryAllByDeletedOrderByName(false,req.toPageable());
        return new QueryResponse(result,entMapper.from(result.getContent()));
    }


    @Override
    public void saveDept(EntDeptByEditRequest req) {
        if(StringUtils.isEmpty(getUserContext().getEntId())) throw new BusinessException("当前企业不为空。");

        EntDeptDO dept;
        if(StringUtils.isEmpty(req.getId())){
            dept = new EntDeptDO();
        }else{
            dept = entDeptDao.getOne(req.getId());
        }

        dept.setName(req.getName());

        if(!StringUtils.isEmpty(req.getParentId())){
            Optional<EntDeptDO> parent = entDeptDao.findById(req.getParentId());
            if(parent.isPresent()){
                dept.setParent(parent.get());
            }
        }
        dept.setEnterprise(entDao.getOne(getUserContext().getEntId()));
        Integer sort = entDeptDao.maxSort(getUserContext().getEntId());
        if(!StringUtils.isEmpty(sort))
            dept.setSort(sort);
        else
            dept.setSort(1);

        entDeptDao.save(dept);
    }

    @Override
    public void deleteEntDept(String id) {
    	EntDeptDO entDeptDO=entDeptDao.findById(id).get();
    	entDeptDO.setDeleted(true);
        entDeptDao.save(entDeptDO);
    }

    @Override
    public  List<EntDeptDTO> listEntDept(String entId) {
        Optional<EntDO> ent = entDao.findById(entId);
        if(!ent.isPresent()) throw new BusinessException("指定的企业没有找到。");
        return entDeptMapper.from(entDeptDao.queryAllByEnterpriseAndDeletedOrderBySort(ent.get(),false));
    }

    @Override
    public EntDeptDTO listEntDeptById(String deptId) {
        Optional<EntDeptDO> result = entDeptDao.findById(deptId);
        if(!result.isPresent()){
            throw new BusinessException("部门信息未找到。");
        }
        return entDeptMapper.from(result.get());
    }

    @Override
    public EntDTO listEntById(String entId) {
        Optional<EntDO> result = entDao.findById(entId);
        if(!result.isPresent()){
            throw new BusinessException("企业信息没有找到。");
        }

        return entMapper.from(result.get());
    }

    @Override
    public void savePersonnel(PersonnelByEditRequest req){
        PersonnelDO obj = null;
        if(StringUtils.isEmpty(req.getId()))
            obj = new PersonnelDO();
        else{
            Optional<PersonnelDO> newObj = personnelDao.findById(req.getId());
            if(!newObj.isPresent()) {
            	throw new BusinessException("指定的人员无效。");
            }else {
				obj=newObj.get();
			}
                
        }

        obj.setName(req.getName());
        obj.setTel(req.getTel());
        obj.setSex(req.getSex());
        obj.setEmail(req.getEmail());

        if(!StringUtils.isEmpty(req.getDeptId())){
            EntDeptDO dept = entDeptDao.getOne(req.getDeptId());
            obj.setDept(dept);
            obj.setEnterprise(dept.getEnterprise());
        }
        personnelDao.save(obj);
    }

    @Override
    public void deletePersonnel(String id) {
    	PersonnelDO personnelDO=personnelDao.findById(id).get();
    	personnelDO.setDeleted(true);
        personnelDao.save(personnelDO);
    }

    @Override
    public QueryResponse<List<PersonnelDTO>> listPersonnelBySearch(QueryRequest req) {
        Optional<EntDO> ent = entDao.findById(this.getUserContext().getEntId());
        if(!ent.isPresent()) throw new BusinessException("没找到指定的企业。");
        Page<PersonnelDO> result = personnelDao.queryAllByEnterpriseAndDeletedAndNameOrTelOrderBySort(ent.get(),false,req.getSearchText(),req.getSearchText(),req.toPageable());
        return new QueryResponse(result,personnelMapper.from(result.getContent()));
    }

    @Override
    public QueryResponse<List<PersonnelDTO>> listPersonnelByDeptId(QueryRequest<String> req) {
        Optional<EntDeptDO> dept = entDeptDao.findById(req.getCondition());
        if(!dept.isPresent()) throw new BusinessException("指定的部门没有找到。");
        Page<PersonnelDO> result = personnelDao.queryAllByDeptAndDeletedOrderBySort(dept.get(),false,req.toPageable());
        return new QueryResponse(result,personnelMapper.from(result.getContent()));
    }

	
}

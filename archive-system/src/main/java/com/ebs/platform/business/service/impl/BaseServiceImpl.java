package com.ebs.platform.business.service.impl;
import com.ebs.platform.core.old.UserContextDTO;
import com.ebs.platform.core.service.IBaseService;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.security.core.context.SecurityContextHolder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * 基础服务
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 9:10
 */
public class BaseServiceImpl implements IBaseService {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public UserContextDTO getUserContext() {
        UserContextDTO userContextDTO = (UserContextDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userContextDTO;
    }

    @Override
    public List<Map> sqlQuery(String sql) {
        return sqlQuery(sql,null);
    }

    @Override
    public List<Map> sqlQuery(String sql, Object[] params) {
        Query query = entityManager.createNativeQuery(sql);
        if(params != null){
            for(int i=0;i<params.length;i++){
                query.setParameter(i+1,params[i]);
            }
        }
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }
    @Override
    public List<Map> sqlQuery(String sql, Object[] params,int firstResult,int lastResult) {
        Query query = entityManager.createNativeQuery(sql);
        if(params != null){
            for(int i=0;i<params.length;i++){
                query.setParameter(i+1,params[i]);
            }
        }
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        query.setFirstResult(firstResult);
        query.setMaxResults(lastResult);
        return query.getResultList();
    }
}

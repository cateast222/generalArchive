package com.ebs.platform.business.dao;

import com.ebs.platform.business.domain.EntDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 10:51
 */
public interface IEntDao extends JpaRepository<EntDO,String>,JpaSpecificationExecutor<EntDO> {

    Page<EntDO> queryAllByDeletedOrderByName(boolean isDelete,Pageable pageable);
}

package com.ebs.platform.business.dao;

import com.ebs.platform.business.domain.AccountDO;
import com.ebs.platform.core.enums.UserTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 9:17
 */
public interface IAccountDao extends JpaRepository<AccountDO,String>,JpaSpecificationExecutor<AccountDO> {

    List<AccountDO> queryAllByUserTypeAndDeleted(UserTypeEnum userType,boolean isDelete);

    AccountDO queryTopByUserName(String userName);

    boolean existsByUserNameAndDeleted(String userName,boolean isDelete);
}

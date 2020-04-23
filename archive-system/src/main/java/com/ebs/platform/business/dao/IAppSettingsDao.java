package com.ebs.platform.business.dao;

import com.ebs.platform.business.domain.AppSettingDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IAppSettingsDao extends JpaRepository<AppSettingDO,String>,JpaSpecificationExecutor<AppSettingDO>
{
    AppSettingDO queryTopByCode(String code);
}

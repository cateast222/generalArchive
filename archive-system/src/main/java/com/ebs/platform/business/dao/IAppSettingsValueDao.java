package com.ebs.platform.business.dao;

import com.ebs.platform.business.domain.AppSettingDO;
import com.ebs.platform.business.domain.AppSettingValueDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IAppSettingsValueDao extends JpaRepository<AppSettingValueDO,String>,JpaSpecificationExecutor<AppSettingValueDO>
{
    List<AppSettingValueDO> queryByAppSetting(AppSettingDO appSettingDO);
}

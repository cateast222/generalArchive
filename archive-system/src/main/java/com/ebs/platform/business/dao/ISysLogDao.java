package com.ebs.platform.business.dao;

import com.ebs.platform.business.domain.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISysLogDao extends JpaRepository<SysLog, String> {
}

package com.ebs.platform.core.conf;

import com.ebs.platform.core.BaseAutoIncrementEntity;
import com.ebs.platform.core.old.UserContextDTO;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * 自动保存更新时间更新人，创建时间创建人
 */
@Configurable
public class AutoSaveGeneralFieldListener {

    @Nullable
    private ObjectFactory<AuditingHandler> handler;

    public void setAuditingHandler(ObjectFactory<AuditingHandler> auditingHandler){
        this.handler=auditingHandler;
    }

    @PrePersist
    public void touchForCreate(BaseAutoIncrementEntity target) {
        UserContextDTO user = ((UserContextDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        target.setOperator(user.getUserId());
        target.setCreateTime(new Date());
        target.setDeleted(false);
        System.out.println("save方法调用前");
    }

    @PreUpdate
    public void touchForUpdate(BaseAutoIncrementEntity target){
        System.out.println("update方法调用前");
        UserContextDTO user = ((UserContextDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        target.setUpdateMan(user.getUserId());
        target.setUpdateTime(new Date());
    }


}

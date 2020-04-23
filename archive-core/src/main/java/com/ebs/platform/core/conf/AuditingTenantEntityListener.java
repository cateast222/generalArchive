package com.ebs.platform.core.conf;

import com.ebs.platform.core.BaseTenantEntity;
import com.ebs.platform.core.old.UserContextDTO;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import java.util.Date;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * JPA审计功能
 */
@Configurable
public class AuditingTenantEntityListener {

    @Nullable
    private ObjectFactory<AuditingHandler> handler;

    public void setAuditingHandler(ObjectFactory<AuditingHandler> auditingHandler){
        this.handler=auditingHandler;
    }

    @PrePersist
    public void touchForCreate(BaseTenantEntity target) {
        UserContextDTO user = ((UserContextDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        target.setCreatedBy(user.getUserId());
        target.setCreatedDate(new Date());
        target.setTenantId(user.getTenantId());

//        if (this.handler != null) {
//            AuditingHandler object = (AuditingHandler) this.handler.getObject();
//            if (object != null) {
//                object.markCreated(target);
//                if (StringUtils.isEmpty(user.getTenantId())) {
//                    LoggerFactory.getLogger(this.getClass()).warn("当前用户 [" + user.getUserName() + "] 没有租户信息。");
//                }else{
//                    target.setTenantId(user.getTenantId());
//                }
//            }
//        }
    }

    @PreUpdate
    public void touchForUpdate(BaseTenantEntity target){
        if (this.handler != null) {
            AuditingHandler object = (AuditingHandler)this.handler.getObject();
            if (object != null) {
                object.markModified(target);
            }
        }
    }


}

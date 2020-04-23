package com.ebs.platform.core;

import com.ebs.platform.core.conf.AuditingTenantEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
@EntityListeners(AuditingTenantEntityListener.class)
@FilterDef(name = "filterByTenant", parameters = {
		@ParamDef(name = "tenantId", type = "string")
})
@Filters({
		@Filter(name = "filterByTenant", condition = "tenant_id = :tenantId")
})
public class BaseTenantEntity extends BaseAuditingEntity {

	@JsonIgnore
	@Column(nullable = false, length = 40)
	private String tenantId;

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
}

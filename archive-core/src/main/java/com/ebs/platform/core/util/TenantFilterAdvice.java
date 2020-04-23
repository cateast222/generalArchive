package com.ebs.platform.core.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Aspect
public class TenantFilterAdvice {
	@PersistenceContext
	private EntityManager entityManager;

	@Around("@within(com.ebs.platform.core.annotation.Tenant)")
	public Object doProcessTenant(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			Filter filter = entityManager.unwrap(Session.class).enableFilter("filterByTenant");
			filter.setParameter("tenantId", PackageUtil.getUserContext().getTenantId());
			return joinPoint.proceed();
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			entityManager.unwrap(Session.class).disableFilter("filterByTenant");
		}
	}

	@Around("@within(com.ebs.platform.core.annotation.LogicDelete) || @within(com.ebs.platform.core.annotation.Tenant)")
	public Object doProcessNotDeleted(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			entityManager.unwrap(Session.class).enableFilter("notDeleted");
			return joinPoint.proceed();
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			entityManager.unwrap(Session.class).disableFilter("notDeleted");
		}
	}
}

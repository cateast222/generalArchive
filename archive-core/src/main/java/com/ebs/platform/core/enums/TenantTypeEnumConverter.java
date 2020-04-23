package com.ebs.platform.core.enums;

import javax.persistence.AttributeConverter;

public class TenantTypeEnumConverter implements AttributeConverter<TenantTypeEnum,Integer> {

    @Override
    public Integer convertToDatabaseColumn(TenantTypeEnum s) {
        return s.getValue();
    }

    @Override
    public TenantTypeEnum convertToEntityAttribute(Integer integer) {
        for(TenantTypeEnum tenantTypeEnum:TenantTypeEnum.values()){
            if(integer.equals(tenantTypeEnum.getValue())){
                return tenantTypeEnum;
            }
        }
        throw new RuntimeException("Unknown database value: " + integer);
    }
}

package com.ebs.platform.core.enums;

import javax.persistence.AttributeConverter;

public class ScopeEnumConverter implements AttributeConverter<ScopeEnum,Integer> {

    @Override
    public Integer convertToDatabaseColumn(ScopeEnum scopeEnum) {
        return scopeEnum.getValue();
    }

    @Override
    public ScopeEnum convertToEntityAttribute(Integer integer) {
        for(ScopeEnum scopeEnum:ScopeEnum.values()){
            if(integer.equals(scopeEnum.getValue())){
                return scopeEnum;
            }
        }
        throw new RuntimeException("Unknown database value: " + integer);
    }
}

package com.ebs.platform.core.enums;

import javax.persistence.AttributeConverter;

public class SexEnumConverter implements AttributeConverter<SexEnum,Integer> {

    @Override
    public Integer convertToDatabaseColumn(SexEnum sexEnum) {
        return sexEnum.getValue();
    }

    @Override
    public SexEnum convertToEntityAttribute(Integer integer) {
        for(SexEnum sexEnum:SexEnum.values()){
            if(integer.equals(sexEnum.getValue())){
                return sexEnum;
            }
        }
        throw new RuntimeException("Unknown database value: " + integer);
    }
}

package com.ebs.platform.core.enums;

import javax.persistence.AttributeConverter;

public class PowerTypeEnumConverter implements AttributeConverter<PowerTypeEnum,Integer> {

    @Override
    public Integer convertToDatabaseColumn(PowerTypeEnum powerTypeEnum) {
        return powerTypeEnum.getValue();
    }

    @Override
    public PowerTypeEnum convertToEntityAttribute(Integer integer) {
        for(PowerTypeEnum powerTypeEnum:PowerTypeEnum.values()){
            if(integer.equals(powerTypeEnum.getValue())){
                return powerTypeEnum;
            }
        }
        throw new RuntimeException("Unknown database value: " + integer);
    }
}

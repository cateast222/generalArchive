package com.ebs.platform.core.enums;

import javax.persistence.AttributeConverter;

public class DataTypeEnumConverter implements AttributeConverter<DataTypeEnum,Integer> {

    @Override
    public Integer convertToDatabaseColumn(DataTypeEnum dataTypeEnum) {
        return dataTypeEnum.getValue();
    }

    @Override
    public DataTypeEnum convertToEntityAttribute(Integer integer) {
        for(DataTypeEnum dataTypeEnum:DataTypeEnum.values()){
            if(integer.equals(dataTypeEnum.getValue())){
                return dataTypeEnum;
            }
        }
        throw new RuntimeException("Unknown database value: " + integer);
    }
}

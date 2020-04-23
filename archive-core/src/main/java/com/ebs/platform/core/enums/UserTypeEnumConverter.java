package com.ebs.platform.core.enums;

import javax.persistence.AttributeConverter;

public class UserTypeEnumConverter implements AttributeConverter<UserTypeEnum,Integer> {

    @Override
    public Integer convertToDatabaseColumn(UserTypeEnum userTypeEnum) {
        return userTypeEnum.getValue();
    }

    @Override
    public UserTypeEnum convertToEntityAttribute(Integer integer) {
        for(UserTypeEnum userTypeEnum:UserTypeEnum.values()){
            if(integer.equals(userTypeEnum.getValue())){
                return userTypeEnum;
            }
        }
        throw new RuntimeException("Unknown database value: " + integer);
    }
}

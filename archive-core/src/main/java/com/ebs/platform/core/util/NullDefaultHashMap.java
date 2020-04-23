package com.ebs.platform.core.util;

import java.util.HashMap;
import java.util.Map;

public class NullDefaultHashMap<T1,T2> extends HashMap<T1,T2> {

    public NullDefaultHashMap(Map<T1,T2> map){
        super(map);
    }

    public T2 GetValueWithDefault(T1 Key,T2 defaultValue){
       T2 value =  this.getOrDefault(Key,null);
       if(value==null){
           return defaultValue;
       }

       return value;
    }
}

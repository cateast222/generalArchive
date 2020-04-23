package com.ebs.platform.core.util;

import com.ebs.platform.core.enums.IEnum;

/**
  *    枚举工具类
 * @author XX
 *   使用方式： 定义枚举需要实现    IEnum  接口
 *   EnumUtil.getEnumByValue(AbbEnum.class, 6)
 */

public class EnumUtil {
	
	// 返回指定 值 的'枚举'
		public static <T extends IEnum> T getEnumByValue(Class<T> myclass, int value) {
	        for(T myEnum : myclass.getEnumConstants())
	            if(value == myEnum.getValue())
	                return myEnum;
	        return null;
	    }
		

	    // 返回指定 名称 的'枚举'
	    public static <T extends IEnum> T getEnumByLable(Class<T> myclass, String lable) {
	        for(T myEnum : myclass.getEnumConstants())
	            if( myEnum.getLabel().equals(lable))
	                return myEnum;
	        return null;
	    }


}

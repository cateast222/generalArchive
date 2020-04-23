package com.ebs.platform.core;


import com.ebs.platform.core.enums.DataTypeEnum;
import com.ebs.platform.core.enums.ResourceData;
import com.ebs.platform.core.enums.ResourceManager;
import com.ebs.platform.core.query.*;
import com.ebs.platform.core.util.PackageUtil;
import com.ebs.platform.core.util.WebResult;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liubo   18855815@qq.com
 * @date 2018-09-10
 * @desc
 */
public abstract class BaseController {

    protected List<MetadataDTO> query(){
        List<MetadataDTO> list = new ArrayList<MetadataDTO>();

        Class cls = this.getClass();
        for (Method m : cls.getDeclaredMethods()) {

            if(m.getAnnotation(MetadataFunction.class)==null)
                continue;

            list.add(query(m.getName()));
        }
        return list;
    }

    protected MetadataDTO query(String name) {

        Class cls = this.getClass();
        Method method = null;

        for (Method m : cls.getDeclaredMethods()) {
            if (m.getAnnotation(MetadataFunction.class) == null)
                continue;
            if (m.getName().equals(name))
                method = m;
        }

        if(method==null)
            return null;
        if(method.getParameters().length<=0)
            return null;

        MetadataDTO obj = new MetadataDTO();

        if(!IMetadataObject.class.isAssignableFrom(method.getParameters()[0].getType())) {
            return null;
        }

        obj.setName(method.getName());
        if(method.getAnnotationsByType(ApiOperation.class)!=null)
            obj.setDescription(method.getAnnotation(ApiOperation.class).value());

        obj.setRequestField(queryRequest(method));
        obj.setResponseField(queryResponse(method));
        return obj;
    }

    private List<MetadataFieldDTO> queryRequest(Method method) {
        String sourceType = ((ParameterizedType)method.getParameters()[0].getParameterizedType()).getActualTypeArguments()[0].getTypeName();
        return queryByClass(method.getParameters()[0].getType(),((ParameterizedType)method.getParameters()[0].getParameterizedType()).getActualTypeArguments()[0],null,sourceType,1);
    }

    private List<MetadataFieldDTO> queryResponse(Method method){
        List<MetadataFieldDTO> ret = new ArrayList<MetadataFieldDTO>();

        if(!(method.getGenericReturnType() instanceof ParameterizedType)){
            System.out.println(method.getName() + " : 返回值未指定泛型对象。");
            return ret;
        }

        try {

            String typeName = ((ParameterizedType)method.getGenericReturnType()).getActualTypeArguments()[0].getTypeName();
            ret = queryByClass(Class.forName(queryTypeName(typeName,1)),null,queryTypeName(typeName,1),typeName,1);

        }catch (Exception ex){
            System.out.println();
            System.out.println("Error : " + ex.fillInStackTrace());
            System.out.println();
        }

        return ret;
    }

    @Deprecated
    private String queryTypeName(String typeName,int index){
        String str = null;
        try {

            switch (index){
                case 1:
                    str = typeName.substring(0, typeName.indexOf("<"));
                    break;
                case 2:
                    str = typeName.replaceAll(queryTypeName(typeName,1) + "<","");
                    str = str.substring(0,str.length()-1);
                    str = queryTypeName(str,1);
                    break;
                case 3:
                    str = typeName.replaceAll(queryTypeName(typeName,1) + "<","");
                    str = str.substring(0,str.length()-1);
                    str = str.substring(str.indexOf("<")+1,str.length() -1);
                    break;
            }
            return str;
        }catch(Exception ex){};

        return null;
    }

    @Deprecated
    private List<MetadataFieldDTO> queryByClass(Class cls,Type pt,String genericTypeName,String sourceTypeName,int levelIndex) {

        List<MetadataFieldDTO> ret = new ArrayList<MetadataFieldDTO>();
        if(!IMetadataObject.class.isAssignableFrom(cls)){
            return ret;
        }

        try{
            for (Field f : cls.getDeclaredFields()) {
                if (f.getAnnotation((MetadataField.class)) == null) {
                    continue;
                }

                MetadataFieldDTO obj = new MetadataFieldDTO();
                obj.setName(f.getName());
                obj.setDisplayName(f.getAnnotation((MetadataField.class)).name());
                obj.setIsArray(0);
                obj.setKey(f.getAnnotation((MetadataField.class)).isKey());
                obj.setColumn(f.getAnnotation((MetadataField.class)).isColumn());
                obj.setFilter(f.getAnnotation((MetadataField.class)).isFilter());
                obj.setFilterHidden(f.getAnnotation((MetadataField.class)).isFilterHidden());

                if (f.getAnnotation(MetadataField.class).resourceType() != null) {
                    obj.setSelectData(queryResource(f.getAnnotation((MetadataField.class))));
                }

                //数据类型验证
                if(f.getGenericType().getTypeName().equals("T")) {
                    if(queryTypeName(cls.getTypeName(),levelIndex+1)==null){
                        obj.setDataType(DataTypeEnum.OBJECT);
                    }
                }
                else{
                    obj.setDataType(getDataType(f.getType(), pt, genericTypeName));
                }

                //根据数据类型处理 ChildField 集合
                if (obj.getDataType() == DataTypeEnum.OBJECT){
                    if(pt==null) {
                        if(f.getGenericType().getTypeName().equals("T")) {
                            int currIndex = levelIndex+1;
                            String str = queryTypeName(sourceTypeName, currIndex);
                            if(List.class.getTypeName().equals(str)) {
                                obj.setIsArray(1);
                                currIndex = currIndex + 1;
                                str = queryTypeName(sourceTypeName, currIndex);
                            }

                            obj.setChildField(queryByClass(Class.forName(str), null, str, sourceTypeName, currIndex));
                            System.out.println(str);
                        }else {
                            obj.setChildField(queryByClass(Class.forName(queryTypeName(sourceTypeName, levelIndex + 1)), null, queryTypeName(sourceTypeName, levelIndex + 1), sourceTypeName, levelIndex + 1));
                        }

                    }else{
                        if(f.getGenericType().getTypeName().equals("T")){
                            obj.setChildField(queryByClass(Class.forName(pt.getTypeName()),null,genericTypeName,sourceTypeName,levelIndex+1));
                        }
                    }
                }
                ret.add(obj);
            }
        }catch (Exception ex)
        {
            System.out.println(ex.fillInStackTrace());
        }

        return ret;
    }

    @Deprecated
    private DataTypeEnum getDataType(Class cls,Type pt,String genericTypeName){
        return PackageUtil.getDataType(cls,pt);
//        if(cls==null)
//            return DataTypeEnum.NONE;
//
//        if(IMetadataObject.class.isAssignableFrom(cls)){
//            return DataTypeEnum.OBJECT;
//        }else if(cls.getName().equals(String.class.getName())){
//            return DataTypeEnum.STRING;
//        }else if(cls.getName().equals(int.class.getName())) {
//            return DataTypeEnum.INT;
//        }else if(cls.getName().equals(Integer.class.getName())){
//            return DataTypeEnum.INT;
//        }else if(cls.getName().equals(Date.class.getName())) {
//            return DataTypeEnum.DATE;
//        }
//        else if(cls.getName().equals(double.class.getName())){
//            return DataTypeEnum.NUMBER;
//        }else{
//            if(pt==null){
//                //System.out.println("Class Name : " + cls.getName());
//                //System.out.println((ParameterizedType)cls instanceof ParameterizedType);
//                return DataTypeEnum.NONE;
//            }else{
//                return DataTypeEnum.OBJECT;
//            }
//        }
    }

    private List<ResourceData> queryResource(MetadataField resourceAnnotation){
        if(resourceAnnotation==null)
            return new ArrayList<ResourceData>();

        return ResourceManager.getInstance().queryResource(resourceAnnotation.resourceType());
    }

    private List<ResourceData> queryResource(Resource resource){
        if(resource==null)
            return new ArrayList<ResourceData>();

        return ResourceManager.getInstance().queryResource(resource.name());
    }

    protected abstract WebResult queryMetadata();

    protected abstract WebResult queryMetadataByName(String name);
}
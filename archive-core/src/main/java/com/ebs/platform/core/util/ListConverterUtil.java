package com.ebs.platform.core.util;

import com.google.common.collect.Lists;
//import org.apache.commons.beanutils.ConvertUtils;
//import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.cglib.beans.BeanMap;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by liujie on 2019/6/19.
 */
public class ListConverterUtil {

    /**
     * 将字符串的集合转换成sql中的in条件
     * @param list
     * @return
     */
    public static String listToInCondition(List<String> list){
        StringBuffer condition = new StringBuffer("( '");
        for(int i = 0; i<list.size(); i++){
            condition.append(list.get(i)).append("','");
        }
        String substring = condition.substring(0, condition.length() - 2);
        return substring+")";
    }

    /**
     * 将List<Map<String,Object>>转换为List<T>
     * @param maps
     * @param clazz
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T> List<T> mapsToObjects(List<Map> maps,Class clazz) throws InstantiationException, IllegalAccessException {
        List<T> list = Lists.newArrayList();
        if (maps != null && maps.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (int i = 0,size = maps.size(); i < size; i++) {
                map = maps.get(i);
                bean = (T) clazz.newInstance();
                mapToBean(map, bean);
                list.add(bean);
            }
        }
        return list;
    }

    /**
     * 将map装换为javabean对象
     * @param map
     * @param bean
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map,T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }


    public static List parse(List list,Class obj){
        //生成集合
        ArrayList ary = new ArrayList();
        //遍历集合中的所有数据
        for(int i = 0; i<list.size(); i++){
            try {
                ////生成对象实历 将MAP中的所有参数封装到对象中
                Object o = addProperty( (Map)list.get(i),obj.newInstance() );
                //把对象加入到集合中
                ary.add(o);
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //返回封装好的集合
        return list;
    }



    /**Map对象中的值为 name=aaa,value=bbb
     调用方法
     addProperty(map,user);
     *将自动将map中的值赋给user类
     *此方法结合Spring框架的jdbcTemplete将非
     *常有用
     *
     * @return封装好的对象
     */
//    @param map存储着名称和值集合
//     * @param obj要封装的对象
    public static Object addProperty(Map map,Object obj){
        //遍历所有名称
//        Iterator it = map.keySet().iterator();
//        while(it.hasNext()){
//            //取得名称
//            String name = it.next().toString();
//            //取得值
//            Object o = map.get(name);
//            String value = o==null?null:o.toString();
//            try{
//                //取得值的类形
//                Class type = PropertyUtils.getPropertyType(obj, name);
//                if(type!=null){
//                    //设置参数
//                    PropertyUtils.setProperty(obj, name, ConvertUtils.convert(value, type));
//                }
//            }catch(Exception ex){
//                ex.printStackTrace();
//            }
//        }
        return obj;

    }


    public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }


    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        System.out.println(listToInCondition(list));
    }
}

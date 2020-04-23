package com.ebs.platform.core.util;

import com.ebs.platform.core.conf.DictionaryDefine;
import com.ebs.platform.core.dto.AppDictValueDTO;
import com.ebs.platform.core.dto.DictionaryObject;
import com.ebs.platform.core.enums.DataTypeEnum;
import com.ebs.platform.core.enums.DictionaryValueStyleEnum;
import com.ebs.platform.core.enums.IEnum;
import com.ebs.platform.core.enums.ResourceData;
import com.ebs.platform.core.metadata.EnumInfo;
import com.ebs.platform.core.metadata.FunctionInfo;
import com.ebs.platform.core.metadata.ServiceInfo;
import com.ebs.platform.core.old.UserContextDTO;
import com.ebs.platform.core.query.*;
import com.ebs.platform.core.service.IPlatformService;
import com.ebs.platform.core.service.IRedisTemplateService;
import com.ebs.platform.core.service.impl.PlatformService;
import com.ebs.platform.core.service.impl.RedisTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.lang.reflect.*;

import jdk.internal.dynalink.beans.StaticClass;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Stream;

@Component
public class PackageUtil {

    @Autowired
    private IPlatformService platformService;

//    @Autowired
//    private  StringRedisTemplate stringRedisTemplate;

//    @Autowired
//    private IRedisTemplateService iRedisTemplateService;

    private static IPlatformService service;

//    private static StringRedisTemplate stringRedisTemplate2;
//    @PostConstruct
//    public void init(){
//        service=this.platformService;
//        stringRedisTemplate2=this.stringRedisTemplate;
//    }

    /** jar中的文件路径分隔符 */
    private static final char SLASH_CHAR = '/';
    /** 包名分隔符 */
    private static final char DOT_CHAR = '.';

    public static final String defaultPackageName = "com.ebs";

    /**
     * 在指定 pageageName 中，检查是否有实现指定的接口 type ，检查过程中，排除指定的 exclude type
     * @param packageName 要搜索的包名
     * @param targetInterface 指定的接口类型
     * @param excludeImplClass 要排除的实现
     * @return
     */
    public static Class getInterfaceImpl(String packageName, Type targetInterface,Type excludeImplClass){

        if (StringUtils.isEmpty(packageName)) packageName = defaultPackageName;

        List<Class<?>> list = getClass(packageName,true);
        for(Class<?> cls : list) {
            for (Type t : cls.getGenericInterfaces()) {

                if(!t.getTypeName().equals("com.ebs.business.customer.core.web.CustomerCallService")) continue;

                //如果当前cls没有实现 targetInterface 则继续
                if(t!=targetInterface)continue;

                //如果没有指定要排除的 excludeImplClass 则直接返回当前 class
                if(excludeImplClass==null) return cls;

                //如果当前cls是要排队的实现类 excludeImplClass，则继续
                if (cls.getName().equals(excludeImplClass.getClass().getName())) continue;

                return cls;
            }
        }
        return null;
    }

    public static UserContextDTO getUserContext(){
        UserContextDTO userContextDTO = (UserContextDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userContextDTO;
    }

    public static String getDictValue(String key, String value, DictionaryValueStyleEnum style){
        // TODO: 2018-12-12 此处应做缓存处理
        AppDictValueDTO objValue = service.getDictValue(getUserContext().getToken(),key,value);
        if(objValue==null) return null;
        switch (style){
            case name:
                return objValue.getName();
            case value:
                return objValue.getValue();
            case valueAndName:
                return objValue.getValue() + "-" + objValue.getName();
            default:
                return null;
        }
    }

    public static String getDictNameById(String dictValueId){
        // TODO: 2018-12-12 此处应做缓存处理
        if(!StringUtils.isEmpty(dictValueId)){
            String name = "";//stringRedisTemplate2.opsForValue().get(dictValueId);
            return name;
        }
        return null;
    }

    public static Object setDictionaryValue(Object obj) {
//        for (Field field : obj.getClass().getDeclaredFields()) {
//            DictionaryKey key = field.getAnnotation(DictionaryKey.class);
//            if (key == null) continue;
//
//            try {
//                field.setAccessible(true);
//                System.out.println(field.getName() +" : "+ field.get(obj));
//                switch (key.valueStyle()) {
//                    case value:
//                        field.set(obj, "value");
//                        break;
//                    case name:
//                        field.set(obj, "name");
//                        break;
//                    case valueAndName:
//                        //field.set(obj, "value-name");
//                        if(field.get(obj)!=null) {
//                            AppDictValueDTO dictValue = service.getDictValue(getUserContext().getToken(), key.key(), field.get(obj).toString());
//                            field.set(obj, dictValue.getValue() + "-" + dictValue.getName());
//                        }
//                        break;
//                }
//                System.out.println(field.getName() + " : " + field.get(obj));
//                System.out.println();
//            } catch (IllegalAccessException ex) {
//                ex.printStackTrace();
//            }
//        }
        return obj;
    }

    /**
     * 获取指定包中，所有实现了 IEnum 接口的枚举类型
     * @param packageName
     * @return
     */
    public static Map<String,List<EnumInfo>> getEnums(String packageName){
        if(StringUtils.isEmpty(packageName)) packageName=defaultPackageName;

        Map<String,List<EnumInfo>> map= new HashMap<>();
        List<Class<?>> list = PackageUtil.getClass(packageName,true);
        for (Class<?> cls: list){
            if(!cls.isEnum()){
                continue;
            }
            System.out.println(cls.getTypeName());
            if(IEnum.class.isAssignableFrom(cls)){
                map.put(cls.getName(),getEnums(cls));
            }else{
                LoggerFactory.getLogger(PackageUtil.class).warn("枚举类型 " + cls.getName() + " 没有实现 IEnum 接口, 将不能被被前端调用.");
            }
        }
        return map;
    }

    public static List<EnumInfo> getEnums(Class enumType){

        List<EnumInfo> result = new ArrayList<>();
        if(!enumType.isEnum()) return result;

        try{
            Method m = enumType.getMethod("values");
            IEnum enums[] = (IEnum[])m.invoke(null);
            for(IEnum e : enums){
                if(e.isJsonIgonre()) continue;
                result.add(new EnumInfo(e.toString(),e.getLabel()));
            }
        }catch (Exception ex){
            LoggerFactory.getLogger(PackageUtil.class).error("加载枚举类型异常.",ex);
        }
        return result;
    }

    /**
     * 获取指定包中，所有包含 @RestController 注解的 class，并将其以 ServiceInfo 的数据结构返回
     * @param packageName
     * @return
     */
    public static List<ServiceInfo> getControllers(String packageName) {
        List<Class<?>> list = PackageUtil.getClass(packageName, true);
        List<ServiceInfo> serviceList = new ArrayList();
        for (Class<?> cls : list) {
            RestController controllerAnnotation = cls.getAnnotation(RestController.class);
            if (controllerAnnotation == null) {
                continue;
            }
            Api api = cls.getAnnotation(Api.class);
            serviceList.add(
                    new ServiceInfo(
                            cls.getName(),
                            controllerAnnotation.value(),
                            api == null ? "" : api.value(),
                            getFunctions(cls)));
        }
        return serviceList;
    }

    /**
     *  获取一个 class 中所有包含 @RequestMapping 注解的 method ，并将其以 FunctionInfo 的数据结构返回
     * @param cls
     * @return
     */
    public static List<FunctionInfo> getFunctions(Class<?> cls){
        List<FunctionInfo> result = new ArrayList();
        String rootPathName = null;
        if(cls.getAnnotation(RequestMapping.class).value().length<=0)
            return null;

        rootPathName = cls.getAnnotation(RequestMapping.class).value()[0];

        for(Method m : cls.getMethods()){
            String name = null;
            RequestMapping requestMapping = m.getAnnotation(RequestMapping.class);
            ApiOperation apiOperation = m.getAnnotation(ApiOperation.class);
            MetadataFunction funMethod = m.getAnnotation(MetadataFunction.class);

            if(funMethod==null) continue;

            if(requestMapping!=null){
                name = requestMapping.value()[0];
            }else{
                PostMapping postMapping = m.getAnnotation(PostMapping.class);
                if(postMapping!=null){
                    name = postMapping.value()[0] ;
                }else{
                    GetMapping getMapping = m.getAnnotation(GetMapping.class);
                    if(getMapping!=null){
                        name = getMapping.value()[0] ;
                    }else{
                        return null;
                    }
                }
            }

            rootPathName = rootPathName.replace("/*","");
            if(!rootPathName.substring(0,1).equals("/"))
                rootPathName = "/" + rootPathName;

            if (name.substring(0,1).equals("/")) {
                name = rootPathName + name;
            }else{
                name = rootPathName + "/" + name;
            }
            result.add(new FunctionInfo(
                    StringUtils.isEmpty(name) ? m.getName() : name,
                    apiOperation==null ? null : apiOperation.value(),
                    requestMapping==null ? null : requestMapping.name(),
                    requestMapping==null ? null : requestMapping.method(),
                    getRequest(m),
                    getResponse(m)));
        }
        return result;
    }

    /**
     * 获取 method 输入参数的数据结构
     * @param method
     * @return
     */
    private static List<MetadataFieldDTO> getRequest(Method method){
        if(method.getParameterCount()<=0) return null;
        if(method.getParameterCount()>0 && method.getParameters()[0].getParameterizedType() !=null) {
            String sourceType = ((ParameterizedType) method.getParameters()[0].getParameterizedType()).getActualTypeArguments()[0].getTypeName();
            return getMetadata(method.getParameters()[0].getType(), ((ParameterizedType) method.getParameters()[0].getParameterizedType()).getActualTypeArguments()[0], sourceType, 1);
        }
        return null;
    }

    /**
     * 获取 method 返回值的数据结构
     * @param method class.method 对象
     * @return 返回值的数据结构
     */
    private static List<MetadataFieldDTO> getResponse(Method method){
        if(!(method.getGenericReturnType() instanceof ParameterizedType)){
            System.out.println(method.getName() + " : 返回值未指定泛型对象。");
            return null;
        }

        try {
            String typeName = ((ParameterizedType)method.getGenericReturnType()).getActualTypeArguments()[0].getTypeName();
            return getMetadata(Class.forName(getTypeName(typeName,1)),null,typeName,1);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static List<AppDictValueDTO> getDictValues(String key) {
        List<AppDictValueDTO> result = new ArrayList();
        try {
            String token = ((UserContextDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getToken();
            result = service.listDictValueByDictCode(token, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据类型，生成 MetadataField 数据结构
     * @param cls
     * @param pt
     * @param sourceTypeName
     * @param levelIndex
     * @return
     */
    public static List<MetadataFieldDTO> getMetadata(Class cls,Type pt,String sourceTypeName,int levelIndex) {
        List<MetadataFieldDTO> ret = new ArrayList<MetadataFieldDTO>();
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
                obj.setFilter(f.getAnnotation(MetadataField.class).isFilter());
                obj.setFilterHidden(f.getAnnotation(MetadataField.class).isFilterHidden());

                //加载枚举类型
                if(f.getType().isEnum()){
                    obj.setSelectData(ResourceData.fromEnum(f.getName(),f.getType()));
                }
                //加载字典
                DictionaryKey dictKey = f.getAnnotation(DictionaryKey.class);
                if(dictKey!=null){
                    obj.setSelectData(ResourceData.fromDictionary(dictKey.key(),getDictValues(dictKey.key())));
                }

                //数据类型验证
                if(f.getGenericType().getTypeName().equals("T")) {
                    if(getTypeName(cls.getTypeName(),levelIndex+1)==null){
                        obj.setDataType(DataTypeEnum.OBJECT);
                    }
                }else{
                    obj.setDataType(getDataType(f.getType(), pt));
                }

                //根据数据类型处理 ChildField 集合
                if (obj.getDataType() == DataTypeEnum.OBJECT){
                    if(pt==null) {
                        if(f.getGenericType().getTypeName().equals("T")) {
                            int currIndex = levelIndex+1;
                            String str = getTypeName(sourceTypeName, currIndex);
                            if(List.class.getTypeName().equals(str)) {
                                obj.setIsArray(1);
                                currIndex = currIndex + 1;
                                str = getTypeName(sourceTypeName, currIndex);
                            }

                            obj.setChildField(getMetadata(Class.forName(str), null, sourceTypeName, currIndex));

                        }else {
                            obj.setChildField(getMetadata(Class.forName(getTypeName(sourceTypeName, levelIndex + 1)), null, sourceTypeName, levelIndex + 1));
                        }

                    }else{
                        if(f.getGenericType().getTypeName().equals("T")){
                            obj.setChildField(getMetadata(Class.forName(pt.getTypeName()),null,sourceTypeName,levelIndex+1));
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

    /**
     * 根据指定的 typeName 计算最终的泛型类型
     * @param typeName 原始 typeName
     * @param index 查询层次，最大值为 3
     * @return typeName 中的泛型类型
     */
    public static String getTypeName(String typeName,int index){
        String str = null;
        try {
            switch (index){
                case 1:
                    str = typeName.substring(0, typeName.indexOf("<"));
                    break;
                case 2:
                    str = typeName.replaceAll(getTypeName(typeName,1) + "<","");
                    str = str.substring(0,str.length()-1);
                    str = getTypeName(str,1);
                    break;
                case 3:
                    str = typeName.replaceAll(getTypeName(typeName,1) + "<","");
                    str = str.substring(0,str.length()-1);
                    str = str.substring(str.indexOf("<")+1,str.length() -1);
                    break;
            }
            return str;
        }catch(Exception ex){};

        return null;
    }

    /**
     * 将根据指定的Class类型，返回对应的描述类型
     * @param cls
     * @return
     */
    public static DataTypeEnum getDataType(Class cls, Type pt){
        if(cls==null) {
            return DataTypeEnum.NONE;
        }else if(IMetadataObject.class.isAssignableFrom(cls)){
            return DataTypeEnum.OBJECT;
        }else if(cls.getName().equals(String.class.getName())){
            return DataTypeEnum.STRING;
        }else if(cls.getName().equals(int.class.getName())) {
            return DataTypeEnum.INT;
        }else if(cls.getName().equals(Integer.class.getName())){
            return DataTypeEnum.INT;
        }else if(cls.getName().equals(Date.class.getName())) {
            return DataTypeEnum.DATE;
        }else if(cls.getName().equals(double.class.getName())) {
            return DataTypeEnum.NUMBER;
        }else if(cls.getName().equals(Double.class.getName())) {
            return DataTypeEnum.NUMBER;
        }else if(cls.getName().equals(Float.class.getName())){
            return DataTypeEnum.NUMBER;
        }else{
            if(pt==null){
                return DataTypeEnum.NONE;
            }else{
                return DataTypeEnum.OBJECT;
            }
        }
    }

    /**
     * 根据指定的接口，找到所有的实现类
     * @return
     */
    public static Map<String,DictionaryObject> getDictionaryDefine() throws IllegalAccessException,InstantiationException{
        return getDictionaryDefine(null);
    }

    /**
     * 根据指定的接口，找到所有的实现类
     * @param packagena
     * @return
     */
    public static Map<String,DictionaryObject> getDictionaryDefine(String packagena) throws IllegalAccessException, InstantiationException {

        if(StringUtils.isEmpty(packagena)) packagena = defaultPackageName;

        List<Class<?>> list = getClass(packagena,true);
        Map<String,DictionaryObject> result = new HashMap<>();

        for(Class<?> cls : list){
            if(DictionaryDefine.class.isAssignableFrom(cls) && !cls.getName().equals(DictionaryDefine.class.getName())){
                for(DictionaryObject item : ((DictionaryDefine) cls.newInstance()).define()){
                    result.put(item.getCode(),item);
                    System.out.println(item.getScope() + " : " + item.getCode());
                }
            }
        }

        return result;
    }
    /**
     * 在当前项目中寻找指定包下的所有类
     *
     * @param packageName 用'.'分隔的包名
     * @param recursive 是否递归搜索
     * @return 该包名下的所有类
     */
    public static List<Class<?>> getClass(String packageName, boolean recursive) {
        if(StringUtils.isEmpty(packageName)) packageName = defaultPackageName;
        List<Class<?>> classList = new ArrayList<Class<?>>();
        try {
            //获取当前线程的类装载器中相应包名对应的资源
            Enumeration<URL> iterator = Thread.currentThread().getContextClassLoader().getResources(packageName.replace(DOT_CHAR, File.separatorChar));

            while (iterator.hasMoreElements()) {
                URL url = iterator.nextElement();
                String protocol = url.getProtocol();
                List<Class<?>> childClassList = Collections.emptyList();
                switch (protocol) {
                    case "file":
                        childClassList = getClassInFile(url, packageName, recursive);
                        System.out.println("进入 switch-file 包名对应的"+packageName);
                        break;
                    case "jar":
                        childClassList = getClassInJar(url, packageName, recursive);
                        System.out.println("进入 switch-jar  包名对应的"+packageName);
                        break;
                    default:
                        //在某些WEB服务器中运行WAR包时，它不会像TOMCAT一样将WAR包解压为目录的，如JBOSS7，它是使用了一种叫VFS的协议
                        System.out.println("unknown protocol " + protocol);
                        break;
                }
                classList.addAll(childClassList);
            }
            return classList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * 在给定的文件或文件夹中寻找指定包下的所有类
     *
     * @param filePath 包的路径
     * @param packageName 用'.'分隔的包名
     * @param recursive 是否递归搜索
     * @return 该包名下的所有类
     */
    public static List<Class<?>> getClassInFile(String filePath, String packageName, boolean recursive) {
        Path path = Paths.get(filePath);
        return getClassInFile(path, packageName, recursive);
    }

    /**
     * 在给定的文件或文件夹中寻找指定包下的所有类
     *
     * @param url 包的统一资源定位符
     * @param packageName 用'.'分隔的包名
     * @param recursive 是否递归搜索
     * @return 该包名下的所有类
     */
    public static List<Class<?>> getClassInFile(URL url, String packageName, boolean recursive) {
        try {
            Path path = Paths.get(url.toURI());
            return getClassInFile(path, packageName, recursive);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * 在给定的文件或文件夹中寻找指定包下的所有类
     *
     * @param path 包的路径
     * @param packageName 用'.'分隔的包名
     * @param recursive 是否递归搜索
     * @return 该包名下的所有类
     */
    public static List<Class<?>> getClassInFile(Path path, String packageName, boolean recursive) {
        if (!Files.exists(path)) {
            return Collections.emptyList();
        }
        List<Class<?>> classList = new ArrayList<Class<?>>();
        if (Files.isDirectory(path)) {
            if (!recursive) {
                return Collections.emptyList();
            }
            try {
                //获取目录下的所有文件
                Stream<Path> stream = Files.list(path);
                Iterator<Path> iterator = stream.iterator();
                while (iterator.hasNext()) {
                    classList.addAll(getClassInFile(iterator.next(), packageName, recursive));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                //由于传入的文件可能是相对路径, 这里要拿到文件的实际路径, 如果不存在则报IOException
                path = path.toRealPath();
                String pathStr = path.toString();
                //这里拿到的一般的"aa:\bb\...\cc.class"格式的文件名, 要去除末尾的类型后缀(.class)
                int lastDotIndex = pathStr.lastIndexOf(DOT_CHAR);
                //Class.forName只允许使用用'.'分隔的类名的形式
                String className = pathStr.replace(File.separatorChar, DOT_CHAR);
                //获取包名的起始位置
                int beginIndex = className.indexOf(packageName);
                if (beginIndex == -1) {
                    return Collections.emptyList();
                }
                className = lastDotIndex == -1 ? className.substring(beginIndex) : className.substring(beginIndex, lastDotIndex);
                classList.add(Class.forName(className));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return classList;
    }

    /**
     * 在给定的jar包中寻找指定包下的所有类
     *
     * @param filePath 包的路径
     * @param packageName 用'.'分隔的包名
     * @param recursive 是否递归搜索
     * @return 该包名下的所有类
     */
    public static List<Class<?>> getClassInJar(String filePath, String packageName, boolean recursive) {
        try {
            JarFile jar = new JarFile(filePath);
            return getClassInJar(jar, packageName, recursive);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * 在给定的jar包中寻找指定包下的所有类
     *
     * @param url jar包的统一资源定位符
     * @param packageName 用'.'分隔的包名
     * @param recursive 是否递归搜索
     * @return 该包名下的所有类
     */
    public static List<Class<?>> getClassInJar(URL url, String packageName, boolean recursive) {
        try {
            JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile();
            return getClassInJar(jar, packageName, recursive);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * 在给定的jar包中寻找指定包下的所有类
     *
     * @param jar jar对象
     * @param packageName 用'.'分隔的包名
     * @param recursive 是否递归搜索
     * @return 该包名下的所有类
     */
    public static List<Class<?>> getClassInJar(JarFile jar, String packageName, boolean recursive) {
        List<Class<?>> classList = new ArrayList<Class<?>>();
        //该迭代器会递归得到该jar底下所有的目录和文件
        Enumeration<JarEntry> iterator = jar.entries();
        while (iterator.hasMoreElements()) {
            //这里拿到的一般的"aa/bb/.../cc.class"格式的Entry或 "包路径"
            JarEntry jarEntry = iterator.nextElement();
            if (!jarEntry.isDirectory()) {
                String name = jarEntry.getName();
                //对于拿到的文件,要去除末尾的.class
                int lastDotClassIndex = name.lastIndexOf(".class");
                if(lastDotClassIndex != -1) {
                    int lastSlashIndex = name.lastIndexOf(SLASH_CHAR);
                    name = name.replace(SLASH_CHAR, DOT_CHAR);
                    if(name.startsWith(packageName)) {
                        if(recursive || packageName.length() == lastSlashIndex) {
                            String className = name.substring(0, lastDotClassIndex);
                            System.out.println(className);
                            try {
                                classList.add(Class.forName(className));
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        return classList;
    }
}
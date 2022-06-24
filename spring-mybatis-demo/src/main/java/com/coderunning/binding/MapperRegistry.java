package com.coderunning.binding;

import cn.hutool.core.lang.ClassScanner;
import com.coderunning.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 提供包路径的扫描和映射器代理类注册机服务，完成接口对象的代理类注册处理
 */
public class MapperRegistry {

    /**
     * 维护已添加的映射器代理类
     */
    private final Map<Class<?>, MapperProxyFactory> knowMappers = new HashMap<>();


    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        // 如果已经注册过，则直接返回
        final MapperProxyFactory<T> mapperProxyFactory = knowMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("Type" + type + "没有注册过该映射器");
        }
        try {
            return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException("创建映射器代理类失败");
        }

    }

    public <T> void addMapper(Class<T> type) {

        if (type.isInterface()) {
            if (hasMapper(type)) {
                throw new RuntimeException("已经注册过该映射器");
            }
        }
        knowMappers.put(type, new MapperProxyFactory<>(type));
    }

    public <T> void addMappers(String packageName) {

        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        for (Class<?> mapperClass : mapperSet) {
            addMapper(mapperClass);
        }
    }

    private boolean hasMapper(Class<?> type) {

        return false;
    }


}


package com.coderunning.binding;

import com.coderunning.session.SqlSession;

import java.lang.reflect.Proxy;
import java.util.Map;

public class MapperProxyFactory<T>{

    // 给谁做代理
    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(SqlSession sqlSession) {

        MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);

       return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);

     }
}

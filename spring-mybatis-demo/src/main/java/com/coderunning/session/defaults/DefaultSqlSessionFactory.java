package com.coderunning.session.defaults;

import com.coderunning.binding.MapperRegistry;
import com.coderunning.session.SqlSession;
import com.coderunning.session.SqlSessionFactory;


/**
 * 默认的简单工厂实现，处理开启 SqlSession 时，
 * 对 DefaultSqlSession 的创建以及传递 mapperRegistry，
 * 这样就可以在使用 SqlSession 时获取每个代理类的映射器对象了
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }


    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}

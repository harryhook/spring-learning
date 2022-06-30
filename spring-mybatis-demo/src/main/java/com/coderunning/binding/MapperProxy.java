package com.coderunning.binding;

import com.coderunning.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 引射器代理类
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {


    private static final long serialVersionUID = 783644796303395867L;

    private SqlSession sqlSession;
    private final Class<T> mapperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // Object 类自带的方法比如equals之类的
        if(Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }
        return "你被代理了 " + sqlSession.getMapper(this.mapperInterface);
    }
}

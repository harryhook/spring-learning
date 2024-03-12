package com.coderunning.factory;

import com.coderunning.aspect.DefaultMethodInterceptor;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author haowei.chen
 * @since 2024/3/12 22:45
 */
public class CglibProxyFactory {

    public static Object getProxy(Class<?> clazz) {
        // 创建动态代理增强类
        Enhancer enhancer = new Enhancer();
        // 设置类加载器
        enhancer.setClassLoader(clazz.getClassLoader());
        // 设置被代理类
        enhancer.setSuperclass(clazz);
        // 设置方法拦截器
        enhancer.setCallback(new DefaultMethodInterceptor());
        // 创建代理类
        return enhancer.create();
    }
}

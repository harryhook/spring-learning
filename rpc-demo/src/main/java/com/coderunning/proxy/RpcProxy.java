package com.coderunning.proxy;

import com.coderunning.handler.RpcInvocationHandler;

import java.lang.reflect.Proxy;

/**
 * @author haowei.chen
 * @since 2024/3/19 22:52
 */
public class RpcProxy {
    public static <T> T createProxy(Class<T> interfaceClass, String host, int port) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new RpcInvocationHandler(host, port));
    }
}
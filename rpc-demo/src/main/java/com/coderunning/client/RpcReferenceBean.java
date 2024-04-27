package com.coderunning.client;

import com.coderunning.proxy.RpcProxy;
import org.springframework.beans.factory.FactoryBean;

public class RpcReferenceBean<T> implements FactoryBean<T> {

    private Class<T> interfaceClass;
    private String host;
    private int port;

    public RpcReferenceBean(Class<T> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    @Override
    public T getObject() throws Exception {
        // 创建代理对象
        return RpcProxy.createProxy(interfaceClass, host, port);
    }

    @Override
    public Class<?> getObjectType() {
        return interfaceClass;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}

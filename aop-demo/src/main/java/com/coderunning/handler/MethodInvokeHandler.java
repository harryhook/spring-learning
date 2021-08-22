package com.coderunning.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MethodInvokeHandler implements InvocationHandler {

    private Object target;


    public MethodInvokeHandler(Object object) {
        this.target = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object res;

        System.out.println("=======before=====" + System.currentTimeMillis());

        res = method.invoke(target, args);

        System.out.println("=======after=====" + System.currentTimeMillis());

        return res;
    }
}

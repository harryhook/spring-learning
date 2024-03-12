package com.coderunning.aspect;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author haowei.chen
 * @since 2024/3/12 22:27
 */

public class DefaultMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("Before invoke " + method);
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("After invoke" + method);
        return result;
    }
}

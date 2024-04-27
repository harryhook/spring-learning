package com.coderunning.processor;

/**
 * @author haowei.chen
 * @since 2024/3/19 23:30
 */
import com.coderunning.annotation.RpcReference;
import com.coderunning.client.RpcClient;
import com.coderunning.provider.HelloService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class RpcReferenceAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            RpcReference rpcReference = field.getAnnotation(RpcReference.class);
            if (rpcReference != null) {
                field.setAccessible(true);
                try {
                    field.set(bean, createRpcReferenceProxy(field.getType()));
                } catch (Exception e) {
                    throw new RuntimeException("Failed to inject RpcReference field", e);
                }
            }
        }
        return bean;
    }

    private <T> T createRpcReferenceProxy(Class<T> interfaceClass) throws Exception {
        // 这里调用 RpcReferenceBean 的工厂方法来创建代理对象
        return (T) RpcClient.helloServiceReference().getObject();
    }
}
package com.coderunning.domain;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

public class Red implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware {
    ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

        System.out.println("注入的ioc容器" + applicationContext);
    }

    @Override
    public void setBeanName(String name) {

        System.out.println("setBeanName " + name);
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        // 进行表达式解析
        String res = stringValueResolver.resolveStringValue("nihao !ddd ${os.name} + #{20-8}");

        System.out.println(res);
    }
}

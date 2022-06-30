package com.coderunning.domain;

import com.coderunning.config.ColorConfig;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Red implements ApplicationContextAware {
    ApplicationContext applicationContext ;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext ;

        System.out.println(applicationContext.getBeanDefinitionNames());
    }
}

package com.coderunning.config;

import com.coderunning.domain.Blue;
import com.coderunning.domain.Oringe;
import com.coderunning.domain.Red;
import com.coderunning.domain.Yellow;
import com.coderunning.processor.MyBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class LifecycleConfig {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    @Scope("prototype")
    public Blue blue() {
        return new Blue();
    }

    @Bean
    public Oringe oringe() {
        return new Oringe();
    }

    @Bean
    public Yellow yellow() {
        return new Yellow();
    }

    @Bean
    public MyBeanPostProcessor myBeanPostProcessor() {
        return new MyBeanPostProcessor();
    }

}

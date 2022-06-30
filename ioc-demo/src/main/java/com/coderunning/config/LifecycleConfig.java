package com.coderunning.config;

import com.coderunning.domain.Blue;
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

}

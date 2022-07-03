package com.coderunning.config;

import com.coderunning.aspect.MyAspect;
import com.coderunning.service.MyCaculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AspectConfig {

    @Bean
    public MyCaculator myCaculator() {
        return new MyCaculator();
    }


    @Bean
    public MyAspect myAspect() {
        return new MyAspect();
    }
}

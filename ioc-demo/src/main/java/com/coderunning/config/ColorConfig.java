package com.coderunning.config;

import com.coderunning.domain.Oringe;
import com.coderunning.factory.ColorFactoryBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({Oringe.class, ColorRegistrar.class, ColorSelector.class})
public class ColorConfig {

    @Bean
    public FactoryBean colorFactoryBean() {
        return new ColorFactoryBean();
    }
}

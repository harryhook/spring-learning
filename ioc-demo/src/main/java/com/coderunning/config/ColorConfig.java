package com.coderunning.config;

import com.coderunning.domain.Orinage;
import com.coderunning.factory.ColorFactoryBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;

@Configuration
@Import({Orinage.class, ColorRegistrar.class, ColorSelector.class})
public class ColorConfig {

    @Bean
    public FactoryBean colorFactoryBean() {
        return new ColorFactoryBean();
    }
}

package com.coderunning.config;

import com.coderunning.dao.ColorDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = {"com.coderunning.service", "com.coderunning.domain", "com.coderunning.dao"})
public class PropertiesConfig {

    @Bean("colorDao2")
    public ColorDao colorDao() {
        ColorDao colorDao =  new ColorDao();
        colorDao.setLabel("2");
        return colorDao;
    }

}

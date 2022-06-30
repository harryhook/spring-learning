package com.coderunning.config;

import com.coderunning.domain.Person;
import com.coderunning.filter.MyFilter;
import com.coderunning.service.IocService;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Configuration
public class PersonConfig {

    @Bean("person")
    @Scope("singleton")
    public Person person() {
        return new Person(18, "张三");
    }
}

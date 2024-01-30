package com.coderunning.config;

import com.coderunning.condition.LinuxCondition;
import com.coderunning.condition.MacCondition;
import com.coderunning.condition.WindowsCondition;
import com.coderunning.domain.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
public class PersonConfig {

    @Bean("person")
    @Lazy
    @Scope("singleton")
    public Person person() {
        System.out.println("创建person bean");
        return new Person(18, "张三");
    }

    @Bean("bill")
    @Conditional(WindowsCondition.class)
    public Person windowsParent() {
        return new Person(18, "Bill Gates");
    }

    @Bean("linus")
    @Conditional(LinuxCondition.class)
    public Person linuxParent() {
        return new Person(60, "Linux");
    }

    @Bean("steve")
    @Conditional(MacCondition.class)
    public Person macParent() {
        return new Person(58, "Steve Jobs");
    }

}

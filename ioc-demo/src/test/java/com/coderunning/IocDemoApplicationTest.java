package com.coderunning;

import com.coderunning.config.PersonConfig;
import com.coderunning.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootTest
public class IocDemoApplicationTest {

    @Test
    public void testXml() {

        String config = "beans.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);

        Person person = context.getBean(Person.class);

        System.out.println(person);
    }

    @Test
    public void testAnnotation() {


        ApplicationContext context = new AnnotationConfigApplicationContext(PersonConfig.class);

        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }

    }
}

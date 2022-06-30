package com.coderunning;

import com.coderunning.config.PersonConfig;
import com.coderunning.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

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

        Person person = context.getBean(Person.class);
        System.out.println(person);
        Person person2 = context.getBean(Person.class);
        System.out.println(person2.equals(person));

    }

    @Test
    public void testScope() {


        ApplicationContext context = new AnnotationConfigApplicationContext(PersonConfig.class);

        Person person = context.getBean(Person.class);
        System.out.println(person);
        Person person2 = context.getBean(Person.class);
        System.out.println(person2);
        System.out.println(person2.equals(person));

    }

    @Test
    public void testConditional() {

        ApplicationContext context = new AnnotationConfigApplicationContext(PersonConfig.class);

        String[] beanNames = context.getBeanNamesForType(Person.class);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
        Map<String, Person> beanTypes = context.getBeansOfType(Person.class);
        System.out.println(beanTypes);

        // 获取环境变量名称
        Environment environment = context.getEnvironment();
        System.out.println(environment.getProperty("os.name"));
    }

}

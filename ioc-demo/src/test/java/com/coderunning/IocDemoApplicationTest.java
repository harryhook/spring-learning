package com.coderunning;

import com.coderunning.config.ColorConfig;
import com.coderunning.config.LifecycleConfig;
import com.coderunning.config.PersonConfig;
import com.coderunning.domain.Blue;
import com.coderunning.domain.Person;
import com.coderunning.domain.Red;
import com.coderunning.factory.ColorFactoryBean;
import org.junit.After;
import org.junit.Before;
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

    @Test
    public void testImport() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ColorConfig.class);
        printBeanDefinitionNames(context);
    }

    @Test
    public void testFactoryBean() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ColorConfig.class);
        Object colorFactoryBean = context.getBean("colorFactoryBean");
        Object colorFactoryBean2 = context.getBean("colorFactoryBean");

        System.out.println(colorFactoryBean.getClass());
        System.out.println(colorFactoryBean.equals(colorFactoryBean2));
        Object colorFactoryBean4 = context.getBean("&colorFactoryBean");
        System.out.println(colorFactoryBean4.getClass());
    }

    @Test
    public void testLifeCycle() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LifecycleConfig.class);

        context.close();
    }

    private void printBeanDefinitionNames(ApplicationContext context) {
        String[] definationNames = context.getBeanDefinitionNames();
        for (String definationName : definationNames) {
            System.out.println(definationName);
        }
    }

}
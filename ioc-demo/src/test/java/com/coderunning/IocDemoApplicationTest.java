package com.coderunning;

import com.coderunning.config.*;
import com.coderunning.dao.ColorDao;
import com.coderunning.domain.Person;
import com.coderunning.service.ColorService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
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

        System.out.println("原始容器： " + context);
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

    @Test
    public void testSetProperties() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PropertiesConfig.class);

        ColorService colorService = context.getBean(ColorService.class);
        System.out.println(colorService);

        ColorDao colorDao = (ColorDao) context.getBean("colorDao");
        System.out.println(colorDao);

        context.close();
    }

    @Test
    public void testProfile() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProfileConfig.class);
        String[] sourceNames = context.getBeanNamesForType(DataSource.class);
        for (String sourceName : sourceNames) {
            System.out.println(sourceName);
        }
    }

    private void printBeanDefinitionNames(ApplicationContext context) {
        String[] definationNames = context.getBeanDefinitionNames();
        for (String definationName : definationNames) {
            System.out.println(definationName);
        }
    }

}
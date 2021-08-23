package com.coderunning;

import com.coderunning.handler.MethodInvokeHandler;
import com.coderunning.service.SomeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

@SpringBootTest
public class AopDemoApplicationTest {

    @Test
    public void test() {
        String config = "applicationContext.xml";


        ApplicationContext context = new ClassPathXmlApplicationContext(config);

        SomeService someService = context.getBean("someService", SomeService.class);


        System.out.println(someService.getClass().getName());
//        InvocationHandler invocationHandler = new MethodInvokeHandler(someService);
//
//        someService = (SomeService) Proxy.newProxyInstance(someService.getClass().getClassLoader(), someService.getClass().getInterfaces(), invocationHandler);

        someService.doSomething();

        someService.doOtherThing();
    }
}

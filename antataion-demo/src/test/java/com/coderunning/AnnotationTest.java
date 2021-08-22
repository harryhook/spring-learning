package com.coderunning;

import com.coderunning.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootTest
public class AnnotationTest {

    @Test
    public void testComponent() {
        String config = "spring-annotation.xml";

        ApplicationContext context = new ClassPathXmlApplicationContext(config);

        Student student = context.getBean("student", Student.class);

        System.out.println(student.toString());
    }
}

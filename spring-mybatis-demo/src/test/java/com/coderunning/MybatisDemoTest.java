package com.coderunning;

import com.coderunning.domain.Student;
import com.coderunning.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

@SpringBootTest
public class MybatisDemoTest {

    @Test
    public void testGenerateDatasource() {

        String config = "applicationContext.xml";

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(config);

        String names[] = applicationContext.getBeanDefinitionNames();

        for (String name : names) {
            System.out.println("===bean names: " + name + "===");
        }
    }

    @Test
    public void testInsert() {

        String config = "applicationContext.xml";

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(config);

        StudentService studentService = applicationContext.getBean("studentService", StudentService.class);

        Student student = new Student();
        student.setName("ZHANGSAN");
        student.setAge(11);
        student.setEmail("zhangsan@163.com");
        studentService.addStudent(student);
    }


    @Test
    public void testSearch() {

        String config = "applicationContext.xml";

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(config);

        StudentService studentService = applicationContext.getBean("studentService", StudentService.class);


        List<Student> studentList = studentService.queryStudent();

        for (Student student : studentList) {
            System.out.println(student.toString());
        }
    }
}

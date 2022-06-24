package com.coderunning;

import com.coderunning.binding.MapperProxyFactory;
import com.coderunning.binding.MapperRegistry;
import com.coderunning.dao.StudentDao;
import com.coderunning.domain.Student;
import com.coderunning.service.StudentService;
import com.coderunning.session.SqlSession;
import com.coderunning.session.SqlSessionFactory;
import com.coderunning.session.defaults.DefaultSqlSessionFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
public class MybatisDemoTest {
    static final Logger logger = LoggerFactory.getLogger(MybatisDemoTest.class);

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

    @Test
    public void testProxy() {

        StudentService studentService = (StudentService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader()
                , new Class[]{StudentService.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return "hello world";
                    }
                });

        String res = studentService.queryStudentByName(null);
        logger.info("res: " + res);
    }

    @Test
    public void testProxyFactory() {

        MapperProxyFactory<StudentService> mapperProxyFactory = new MapperProxyFactory<>(StudentService.class);
        Map<String, String> sqlSession = new HashMap<>();
        // 链接数据库
        sqlSession.put("com.coderunning.service.StudentService.queryStudentByName", "模拟select * from student");

//        StudentService studentService = mapperProxyFactory.newInstance(sqlSession);
//        String res = studentService.queryStudentByName("111");

//        logger.info("res: " + res);
    }


    @Test
    public void testMapperRegistry() {

        // 1. 注册 Mapper
        MapperRegistry registry = new MapperRegistry();
        registry.addMappers("com.coderunning.service");

        // 2. 从 SqlSession 工厂获取 Session
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(registry);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        StudentService studentService = sqlSession.getMapper(StudentService.class);

        logger.info("res: " + studentService.queryStudentByName("111"));
    }
}


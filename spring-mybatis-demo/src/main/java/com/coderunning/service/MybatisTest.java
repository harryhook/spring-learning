package com.coderunning.service;

import com.coderunning.dao.StudentDao;
import com.coderunning.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.List;

public class MybatisTest {
    private static SqlSessionFactory sqlSessionFactory;
    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            StudentDao userMapper = sqlSession.getMapper(StudentDao.class);
            List<Student> user = userMapper.selectStudents();
            System.out.println("User : " + user);
        }
    }
}
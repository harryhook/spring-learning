
# mybatis 的使用步骤

1. 定义 dao 接口 StudentDao
2. 定义 mapper 文件，StudentDao.xml
3. 定义 mybatis 的主配置文件 mybatis.xml
4. 创建 dao 的代理对象 StudentDao dao = SqlSession.getMapper(StudentDao.class);
    List<Student> students = dao.selectStudents();
    

主配置文件：
1. 数据库信息
2. mapper 文件位 置

```mysql
2021-08-24T14:59:46.581558Z 1 [Note] A temporary password is generated for root@localhost: wB0J-ydtUl7X

If you lose this password, please consult the section How to Reset the Root Password in the MySQL reference manual.
```

1. spring 依赖
2. mybatis 依赖
3. mysql 驱动
4. soring 事务
5. mybatis 和 spring 继承依赖， 使spring 创建 mybatis 的 SqlSessionFactory, dao
6. 实体类
7. dao mapper
8. mybatis 配置文件
9. service 接口
10 
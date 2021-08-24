
利用动态代理实现切面

1. 创建目标类
2. 创建 innovationHandler 接口的实现类， 在这个类增加功能
3. jdk 中的 proxy 来创建代理对象
   
   ```java
   Target target = Proxy.newProxyInstance(someService.getClass().getClassLoader(), someService.getClass().getInterfaces(), invocationHandler);

   ```
4. target.doSomething()

1. 动态代理实现方式
    1. 接口通过 JDK Proxy, Method, InvocationHandler
    2. 实现类通过 CgLib, 原理是继承， 通过继承目标类创建子类， 子类就是代理对象， 要求类不能是 final 的， 方法也不是 final 的

动态代理的作用

1. 在不改变目标类代码的情况下， 增加代码功能
2.减少代码重复
3. 专注业务代码逻辑
4.解耦合， 业务逻辑与日志分离
   
如何理解面向切面编程
1. 找到切面？ 哪些业务需要增强
2. JointPoint：连接点
3. PointCut ： 切入点：一个或多多个 jointPoint
4. Advice: 切面的执行时间 @Before， @After, @Around

Spring 自带 aop，但是用的更多的是 aspectj

有接口使用 proxy 动态代理， proxy-target-class="true"时使用 CGLib 
```java
    <aop:aspectj-autoproxy proxy-target-class="true"/>
```


## aspectj 给已经存在的方法增加新的功能

@Before: 方法执行前
@After: 方法执行后
@AfterReturning: 方法执行后, 可以改变返回的结果
@Around: 执行前后都能增强， 能够控制目标方法是否执行， 而且能修改原方法的执行结果

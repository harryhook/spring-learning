# 注解用法
场景注解
@Autowired
@Resource
@Component
@Service
@Controller

@Value ：简单属性赋值
@Qualifier

## @Autired

默认 byType

@Qualifier(value="sd")  byName

@Autowired(required=false)  bean 不存在是返回 null

## @Resource 
也可以自动注入， 默认 byName
先使用 byName 失败时会继续使用 byType


@Component 创建对象

@Service 业务代码
@Repository 持久层类，访问数据库
@Controller 控制层代码


IOC 实现的业务类之间的解耦合，实际是类的创建与使用的解耦合， 将类的创建交给 Spring 来处理
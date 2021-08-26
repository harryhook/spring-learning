# 事务管理器

实现 commit 和 rollback

PlatformTransactionManager 一个接口， 许多实现类

PROPAGATION_REQUIRED: 指定的方法必须在事务内运行，若有事务在当前事务执行， 不存在则创建新的事务
PROPAGATION_REQUIRED_NEW: 新建事务
PROPAGATION_SUPPORTS: 支持事务， 没有也行

# 事务回滚时机：

方法执行过程中， 运行时异常抛出
package com.coderunning.session;

public interface SqlSession {

    /**
     * 根据sqlid获取一条记录的封装对象
     * @param statement
     * @return
     * @param <T>
     */
    <T> T selectOne(String statement);

    /**
     * 根据sqlid获取一条记录的封装对象， 可以传入参数， 可以传入pojo， map或者ImutableMap
     * @param statement
     * @param parameter
     * @return
     * @param <T>
     */
    <T> T selectOne(String statement, Object parameter);


    /**
     * 获取映射器 mapper
     * @param type
     * @return
     * @param <T>
     */
    <T> T getMapper(Class<T> type);
}

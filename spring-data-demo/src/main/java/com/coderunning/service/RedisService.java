package com.coderunning.service;

public interface RedisService {


    void set(String key, String name);

    String get(String key);

}

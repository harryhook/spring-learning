package com.coderunning;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chenhaowei
 */
@SpringBootApplication
@Slf4j
public class SpringJedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJedisApplication.class, args);
        log.info("======启动成功======");

    }
}

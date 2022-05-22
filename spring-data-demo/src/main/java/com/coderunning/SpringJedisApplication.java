package com.coderunning;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

/**
 * @author chenhaowei
 */
@SpringBootApplication
@Slf4j
public class SpringJedisApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(SpringJedisApplication.class, args);
        log.info("======启动成功======");


        RateLimiter limiter = RateLimiter.create(5, 1000, TimeUnit.MILLISECONDS);

        for (int i = 1; i < 50; i++) {
            System.out.println(limiter.acquire());
        }


        Thread.sleep(1000L);

        for (int i = 1; i < 5; i++) {
            System.out.println(limiter.acquire());
        }
    }
}

package com.coderunning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class RpcDemoApplication {


    public static void main(String[] args) {

        SpringApplication.run(RpcDemoApplication.class, args);

        System.out.println("=======================  application start success!  =======================");

    }
}

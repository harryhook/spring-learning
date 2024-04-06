package com.coderunning.consumer;

import com.coderunning.RpcDemoApplication;
import com.coderunning.provider.HelloService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = RpcDemoApplication.class)
public class HelloClientTest {

    @Resource
    HelloClient helloClient;

    @Resource
    HelloService helloService;


    @Test
    public void testCallHello() {

        helloClient.callHello();

    }
}

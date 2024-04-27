package com.coderunning.bootstrap;

import com.coderunning.annotation.RpcReference;
import com.coderunning.provider.HelloService;
import org.springframework.stereotype.Component;

/**
 * @author haowei.chen
 * @since 2024/3/18 19:59
 */
// ClientBootstrap.java
@Component
public class ClientBootstrap {

    @RpcReference
    public HelloService helloService;

    // 这里应该是 Spring 的启动方法，而不是 main 方法
    public void callService() {
        // 调用远程服务
        String result = helloService.sayHello("World");
        System.out.println(result);
    }

}
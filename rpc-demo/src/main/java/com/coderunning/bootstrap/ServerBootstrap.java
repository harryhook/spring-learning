package com.coderunning.bootstrap;

import com.coderunning.provider.HelloService;
import com.coderunning.provider.impl.HelloServiceImpl;
import com.coderunning.server.RpcServer;
import org.springframework.stereotype.Component;

/**
 * @author haowei.chen
 * @since 2024/3/18 19:59
 */
// ServerBootstrap.java
@Component

public class ServerBootstrap {

    public void start() throws Exception {
        // 扫描带有 @RpcService 注解的类并暴露服务
        // 这里简化处理，直接创建 HelloServiceImpl 实例
        HelloService service = new HelloServiceImpl();
        RpcServer.export(service, 8088);
    }
}
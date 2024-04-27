package com.coderunning.client;

/**
 * @author haowei.chen
 * @since 2024/3/19 23:12
 */
import com.coderunning.provider.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RpcClient {

    @Bean
    public static RpcReferenceBean<HelloService> helloServiceReference() {
        return new RpcReferenceBean<>(HelloService.class);
    }
}
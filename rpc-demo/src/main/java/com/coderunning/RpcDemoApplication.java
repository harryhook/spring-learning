package com.coderunning;

import com.coderunning.bootstrap.ClientBootstrap;
import com.coderunning.bootstrap.ServerBootstrap;
import com.coderunning.config.AppConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class RpcDemoApplication {

    public static void main(String[] args) throws Exception {
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
            ClientBootstrap clientBootstrap = context.getBean(ClientBootstrap.class);
            clientBootstrap.callService();
            context.close();
    }
}

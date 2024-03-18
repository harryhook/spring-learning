package com.coderunning.bootstrap;

import com.coderunning.client.RpcClient;
import com.coderunning.req.RpcRequest;

/**
 * @author haowei.chen
 * @since 2024/3/18 19:59
 */
public class ClientBootstrap {
    public static void main(String[] args) throws Exception {
        RpcRequest request = new RpcRequest();
        request.setMethodName("sayHello");
        request.setParameterTypes(new Class<?>[]{String.class});
        request.setParameters(new Object[]{"World"});

        String result = (String) RpcClient.call("localhost", 8080, request);
        System.out.println(result);
    }
}
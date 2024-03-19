package com.coderunning.handler;

/**
 * @author haowei.chen
 * @since 2024/3/19 22:52
 */
// RpcInvocationHandler.java

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class RpcInvocationHandler implements InvocationHandler {

    private String host;
    private int port;

    public RpcInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 发送请求到服务端
        Socket socket = new Socket(host, port);
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        // 发送请求信息（类名、方法名、参数类型、参数值）
        outputStream.writeUTF(method.getDeclaringClass().getName());
        outputStream.writeUTF(method.getName());
        outputStream.writeObject(method.getParameterTypes());
        outputStream.writeObject(args);

        // 读取结果
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        Object result = inputStream.readObject();

        inputStream.close();
        outputStream.close();
        socket.close();

        return result;

    }
}
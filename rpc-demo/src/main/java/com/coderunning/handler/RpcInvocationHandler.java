package com.coderunning.handler;

/**
 * @author haowei.chen
 * @since 2024/3/19 22:52
 */
// RpcInvocationHandler.java

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;
import java.rmi.RemoteException;

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
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        Socket socket = null;

        try {
            socket = new Socket(host, port);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            // 发送请求信息（类名、方法名、参数类型、参数值）
            outputStream.writeUTF(method.getDeclaringClass().getName());
            outputStream.writeUTF(method.getName());
            outputStream.writeObject(method.getParameterTypes());
            outputStream.writeObject(args);

            // 读取结果
            inputStream = new ObjectInputStream(socket.getInputStream());
            Object result = inputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new RemoteException("Remote service call failed", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                socket.close();
            } catch (IOException e) {
                // Log the exception or handle it appropriately
            }
        }

        return null;
    }
}
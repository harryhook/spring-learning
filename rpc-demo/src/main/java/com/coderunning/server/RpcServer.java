package com.coderunning.server;

/**
 * @author haowei.chen
 * @since 2024/3/18 11:57
 */

import com.coderunning.annotation.Server;
import com.coderunning.req.RpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcServer {

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void export(Object service) throws Exception {
        Server server = service.getClass().getAnnotation(Server.class);
        if (server == null) {
            throw new IllegalArgumentException("Server annotation is required");
        }
        int port = server.port();

        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket socket = serverSocket.accept();
            executorService.submit(new ProcessorHandler(socket, service));
        }
    }

    private static class ProcessorHandler implements Runnable {

        private Socket socket;
        private Object service;

        public ProcessorHandler(Socket socket, Object service) {
            this.socket = socket;
            this.service = service;
        }

        @Override
        public void run() {
            try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {
                // 读取请求对象
                RpcRequest request = (RpcRequest) inputStream.readObject();
                // 反射调用
                Object result = invoke(request);
                // 返回结果
                try (ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {
                    outputStream.writeObject(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private Object invoke(RpcRequest request) throws Exception {
            Method method = service.getClass().getMethod(request.getMethodName(), request.getParameterTypes());
            return method.invoke(service, request.getParameters());
        }
    }
}
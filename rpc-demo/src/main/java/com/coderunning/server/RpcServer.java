package com.coderunning.server;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author haowei.chen
 * @since 2024/3/18 11:57
 */
public class RpcServer {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public static void export(Object service, int port) throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket socket = serverSocket.accept();
            executorService.submit(new ProcessorHandler(socket, service));
        }
    }
}



class ProcessorHandler implements Runnable {

    private Socket socket;
    private Object service;

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            // 读取请求信息（类名、方法名、参数类型、参数值）
            String interfaceName = inputStream.readUTF();
            String methodName = inputStream.readUTF();
            Class<?>[] parameterTypes = (Class<?>[]) inputStream.readObject();
            Object[] parameters = (Object[]) inputStream.readObject();

            // 执行调用
            Method method = service.getClass().getMethod(methodName, parameterTypes);
            Object result = method.invoke(service, parameters);

            // 返回结果
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(result);
        } catch (Exception e) {
            e.printStackTrace();
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
                e.printStackTrace();
            }
        }
    }
}
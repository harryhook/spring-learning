package com.coderunning.client;

import com.coderunning.req.RpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RpcClient {
    public static Object call(String host, int port, RpcRequest request) throws Exception {
        try (Socket socket = new Socket(host, port)) {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(request);
            outputStream.flush();

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            return inputStream.readObject();
        }
    }
}
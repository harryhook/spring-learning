package com.coderunning;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServerExample {
    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open(); // 打开Selector

        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress("localhost", 8080));
        serverChannel.configureBlocking(false); // 设置为非阻塞模式
        serverChannel.register(selector, SelectionKey.OP_ACCEPT); // 注册Channel到Selector

        while (true) {
            selector.select(); // 检查是否有事件就绪
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();

                if (key.isAcceptable()) {
                    register(selector, serverChannel);
                }

                if (key.isReadable()) {
                    answerWithEcho(key);
                }

                iter.remove();
            }
        }
    }

    private static void register(Selector selector, ServerSocketChannel serverChannel) throws Exception {
        SocketChannel client = serverChannel.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
    }

    private static void answerWithEcho(SelectionKey key) throws Exception {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(256);
        int bytesRead = client.read(buffer); // 读取数据到buffer中
        if (bytesRead == -1) {
            // 客户端关闭了连接
            client.close();
            System.out.println("Connection closed by client");
            return;
        }
        String receivedMessage = new String(buffer.array(), 0, bytesRead).trim(); // 将读取的数据转换为字符串
        System.out.println("Message received from client: " + receivedMessage);
        buffer.flip();
        client.write(buffer);
    }
}

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClientExample {
    public static void main(String[] args) throws Exception {
        // 创建一个SocketChannel并配置为非阻塞模式
        SocketChannel client = SocketChannel.open();
        client.configureBlocking(true);

        // 尝试连接到服务器
        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 8080);
        client.connect(hostAddress);

        // 在非阻塞模式下，connect()可能不会立即完成连接
        // 因此需要在循环中调用finishConnect()来完成连接
        while (!client.finishConnect()) {
            // 在连接完成之前可以做其他工作
            System.out.println("Connecting to Server...");
        }

        System.out.println("Connected to Server");

        // 发送消息到服务器
        String message = "Hello from Client！！！！！!";
        ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
        client.write(buffer);
        buffer.clear();

        // 接收服务器的回显消息
        ByteBuffer readBuffer = ByteBuffer.allocate(256);
        int readBytes = client.read(readBuffer);

        // 确保有数据可读
        if (readBytes > 0) {
            String receivedMessage = new String(readBuffer.array()).trim();
            System.out.println("Message received from server: " + receivedMessage);
        }

        // 关闭连接
        client.close();
    }
}

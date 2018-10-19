package cn.joey.socket;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerLength {
    public static void main(String[] args) {
        //监听指定的端口
        int port=3333;
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("server 将一直等到连接的到来");
            Socket socket = server.accept();
            InputStream inputStream = socket.getInputStream();
            byte[] bytes;
            while(true){
                //首先读取两个字节表示的长度
                int first = inputStream.read();
                //如果读取的值为-1，说明到了流的末尾，socket已经被关闭了，此时将不能再去读取
                if (first == -1) {
                    break;
                }
                int second = inputStream.read();
                int length=(first<<8) +second;
                //然后构造一个指定长的消息即可
                bytes = new byte[length];
                //然后读取指定长度的消息即可
                inputStream.read(bytes);
                System.out.println("get message from client:"+new String(bytes,"UTF-8"));

            }
            inputStream.close();
            socket.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

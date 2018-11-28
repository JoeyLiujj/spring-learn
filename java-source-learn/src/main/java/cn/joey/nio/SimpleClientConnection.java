package cn.joey.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @auther liujiji
 * @date 2018/11/28 16:24
 */
public class SimpleClientConnection {
    public static void main(String[] args) {
        String hostIp="127.0.0.1";
        int hostListenningPort = 8000;

        SocketChannel socketChannel ;
        boolean mFlag = true;
        try {
            socketChannel = SocketChannel.open(new InetSocketAddress(hostIp,hostListenningPort));
            socketChannel.configureBlocking(false);
            String message = "test-----.......................";
            ByteBuffer writeBuffer = ByteBuffer.wrap(message.getBytes("UTF-8"));
            socketChannel.write(writeBuffer);
            while(mFlag){
                //使通道可读了
                Scanner scan = new Scanner(System.in);
                String str = scan.next();
                ByteBuffer buffer = ByteBuffer.wrap(str.getBytes("UTF-8"));
                //当我往通道中写数据后，导致服务端的注册的通道变为可写
                socketChannel.write(buffer);

            }

        } catch (IOException e) {
            mFlag = false;
        }finally {
            mFlag=false;
        }
    }
}

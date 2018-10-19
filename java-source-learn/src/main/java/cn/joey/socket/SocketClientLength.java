package cn.joey.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClientLength {
    public static void main(String[] args) {
        //要连接到的服务器的IP地址和端口号
        String host = "127.0.0.1";
        int port = 3333;

        try {

            for(int i=0;i<100;i++){

                Socket socket = new Socket(host, port);
                OutputStream outputStream = socket.getOutputStream();
                String message = "你好  how old are you!--- "+i;
                //然后将消息的长度优先发送出去
                byte[] sendBytes = message.getBytes("UTF-8");

                outputStream.write(sendBytes.length>>8);
                outputStream.write(sendBytes.length);
                //然后将消息再次发送出去
                outputStream.write(sendBytes);
                outputStream.flush();
                //关闭输出流

                outputStream.close();
                socket.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

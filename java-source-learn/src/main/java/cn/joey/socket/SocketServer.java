package cn.joey.socket;

import java.io.*;
import java.net.ServerSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(10001);
        Socket socket = server.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String receive = in.readLine();
        System.out.println("收到客户端的信息:"+receive);

        BufferedWriter writer = new BufferedWriter(new PrintWriter(socket.getOutputStream()));

        writer.write("往客户端写diamante");
        writer.flush();
        socket.shutdownOutput();

/**
 * 本类为后续学习的基础
 */
//    public static void main(String[] args) throws IOException {
//        int port =55533;
//        ServerSocket server=new ServerSocket(port);
//
//        long time = System.currentTimeMillis();
//
//        System.out.println(time+"server将一直等待连接的到来");
//        Socket socket = server.accept();
//
//        InputStream inputStream = socket.getInputStream();
//        byte[] bytes = new byte[1024];
//        int len;
//        StringBuilder sb=new StringBuilder();
//
//        while((len=inputStream.read(bytes))!=-1){
//            sb.append(new String(bytes, 0, len, "UTF-8"));
//        }
//        System.out.println(time+"get message from client:"+sb);
//
//        OutputStream outputStream = socket.getOutputStream();
//        outputStream.write("Hello Client, I get the message:".getBytes("UTF-8"));
//
//        inputStream.close();
//        outputStream.close();
//        socket.close();
//        server.close();
//    }
    }
}

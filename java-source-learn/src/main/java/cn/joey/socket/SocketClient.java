package cn.joey.socket;

import java.io.*;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",10001);

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        System.out.println("客户端打印输入的值："+s);
        out.write(s);
        out.flush();
        System.out.println("客户端发送结束！");
        socket.shutdownOutput();


        InputStream inputStream = socket.getInputStream();
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(inputStream));
        String s1 = reader2.readLine();
        System.out.println("接收到服务端发送的消息："+s1);

//        socket.close();
    }
//    public static void main(String[] args) throws IOException {
//        String host="127.0.0.1";
//        int port = 55533;
//        Socket socket = new Socket(host,port);
//
//        //建立连接后获得输出流
//        OutputStream outputStream = socket.getOutputStream();
//        String message = "你好 易烊千玺";
//        socket.getOutputStream().write(message.getBytes("UTF-8"));
//        //通过shutdownOutput高速服务器已经发送完数据，后续只能接受数据
//        socket.shutdownOutput();
//
//        InputStream inputStream = socket.getInputStream();
//        byte[] bytes = new byte[1024];
//        int len ;
//        StringBuilder sb = new StringBuilder();
//        while((len=inputStream.read(bytes))!=-1){
//            //注意制定编码方式，发送方和接收方一定要统一，建议使用UTF-8
//            sb.append(new String(bytes,0,len,"UTF-8"));
//        }
//        long time = System.currentTimeMillis();
//        System.out.println(time+"get message from server:"+sb);
//        inputStream.close();
//        outputStream.close();
//        socket.close();
//    }
}

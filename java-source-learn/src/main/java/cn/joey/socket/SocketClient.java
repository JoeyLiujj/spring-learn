package cn.joey.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",10000);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream());

        BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));

        while(true){
            String msg = reader.readLine();
            out.println(msg);
            out.flush();

            System.out.println(in.readLine());
            if (msg.equals("bye")) {
                break;
            }
//            String s = in.readLine();
//
//            if (s.equals("Server received:bye")) {
//                break;
//            }else{
//                System.out.println(s);
//            }

        }

        socket.close();
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

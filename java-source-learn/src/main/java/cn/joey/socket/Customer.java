package cn.joey.socket;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Customer {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",8081);
            BufferedOutputStream stream = new BufferedOutputStream(socket.getOutputStream());
//            stream.write("Client Query".getBytes());
            stream.write("end".getBytes());
            stream.flush();
            //要进行连接的关闭，否则会一致处于连接的状态，而不能关闭输出流
            // 如果不进行关闭的话，服务端会一直等待客户端的数据，会造成两端同时进行等待
            socket.shutdownOutput();


            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String info;
            while ((info=br.readLine())!=null) {
                System.out.println(info);
            }
            br.close();
            stream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package cn.joey.socket;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Producer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8081);
            Socket accept = serverSocket.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            String info;
            while ((info=br.readLine())!=null&&"end".equals(info)) {
                System.out.println(info);
                System.out.println("Receiver Query");
            }

            accept.shutdownInput();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
            bw.write("Client Query profile");
            bw.write("Client Query");
            bw.write("Client Query quit");
            bw.flush();
            bw.close();
            br.close();
            accept.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package cn.joey.bio;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @auther liujiji
 * @date 2018/11/22 9:02
 */
public class ChatServer {

    private static final int PORT = 8888;

    public static void main(String[] args) {
        try {
            ChatServer.receive();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void receive() throws IOException {
        ServerSocket serverSocket =null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("服务器启动。。。");
            while(true){
                Socket accept = serverSocket.accept();
                new Thread(new Chat(accept)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket!=null){
                serverSocket.close();
            }
        }
    }

    static class Chat implements Runnable{

        Socket socket;
        public Chat(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                int port = socket.getPort();
                System.out.println(port+"接受任务并开始执行。。。");
                InputStream inputStream = socket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String result = "";
                String line;
                while((line = br.readLine())!=null){
                    result +=line;
                }
                System.out.println("客户端发送的消息是："+result);
                socket.shutdownInput();

                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                System.out.println("服务器为每一个客户端发送其他客户端的消息，以便其他客户端接受消息。");
                bw.write(result);
                bw.flush();
                socket.shutdownOutput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package cn.joey.bio;

import java.io.*;
import java.net.Socket;

public class ChatClient {
    private static int DEFAULT_SERVER_PORT = 8888;
    private static String DEFAULT_SERVER_IP= "127.0.0.1";

    public static void main(String[] args) {
        ChatClient.send();
    }

    public static void send() {
        Socket socket = null;
        try {

            InputStream in = System.in;
            BufferedReader br1 = new BufferedReader(new InputStreamReader(in));
            while(true){
                socket = new Socket(DEFAULT_SERVER_IP,DEFAULT_SERVER_PORT);
                OutputStream outputStream = socket.getOutputStream();
                String sendMessage = br1.readLine();
                System.out.println("客户端：发送一条消息");
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
                if("bye".equals(sendMessage)){
                    bw.write(sendMessage);
                    bw.flush();
                    break;
                }
                bw.write(sendMessage);
                bw.flush();
                socket.shutdownOutput();

                //接受服务端发送的其他客户端的消息
                InputStream inputStream = socket.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String result = "";
                String line ;
                while((line=br.readLine())!=null){
                    result +=line;
                }
                System.out.println(result);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

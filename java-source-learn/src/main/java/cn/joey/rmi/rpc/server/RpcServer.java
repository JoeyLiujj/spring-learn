package cn.joey.rmi.rpc.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @auther liujiji
 * @date 2019/1/9 15:43
 */
public class RpcServer {

    private static final ExecutorService executorService= Executors.newCachedThreadPool();

    public void publish(Object service,int port) throws IOException {
        ServerSocket serverSocket =null;
        try {
            serverSocket = new ServerSocket(port);
            while(true){
                Socket socket = serverSocket.accept();
                executorService.execute(new ProcessHandler(socket,service));
                executorService.shutdown();
            }
        } catch (IOException e) {
            throw new RuntimeException("服务端连接出错");
        }finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }
}

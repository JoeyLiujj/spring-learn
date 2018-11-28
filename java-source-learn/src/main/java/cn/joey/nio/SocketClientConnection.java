package cn.joey.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * @auther liujiji
 * @date 2018/11/28 10:51
 */
public class SocketClientConnection {

    private Selector selector;

    SocketChannel socketChannel;

    //要连接的服务器的IP
    private String hostIp;

    //要连接的远程服务器监听的端口
    private int hostListenningPort;
    static SocketClientConnection client;
    static boolean mFlag = true;

    public SocketClientConnection(String hostIp,int hostPort) throws IOException {
        this.hostIp = hostIp;
        this.hostListenningPort = hostPort;
        init();
    }

    private void init() throws IOException {
        socketChannel = SocketChannel.open(new InetSocketAddress(hostIp,hostListenningPort));
        socketChannel.configureBlocking(false);

        //创建选择器，并把通道注册到选择器中
//        selector = Selector.open();
//        socketChannel.register(selector, SelectionKey.OP_READ);

//        new TCPClientReadThread(selector);
    }

    public void sendMsg(String message) throws IOException {
        ByteBuffer writeBuffer = ByteBuffer.wrap(message.getBytes("UTF-8"));
        socketChannel.write(writeBuffer);
    }
    public static void main(String[] args) throws IOException {
        client = new SocketClientConnection("127.0.0.1",8000);
        new Thread(){
            @Override
            public void run() {
                try {
                    client.sendMsg("test-----.......................");
                    while(mFlag){
                        Scanner scan = new Scanner(System.in);
                        String str = scan.next();
                        client.sendMsg(str);
                    }
                } catch (IOException e) {
                    mFlag=false;
                }finally {
                    mFlag = true;
                }
                super.run();
            }
        }.start();
    }


    private class TCPClientReadThread implements Runnable{
        Selector selector;
        public TCPClientReadThread(Selector selector) {
            super();
            this.selector = selector;
            new Thread(this).start();
        }

        @Override
        public void run() {
            try{
                while(selector.select() > 0){
                    //遍历每个IO操作Channel对应的SelectionKey
                    for(SelectionKey sk:selector.selectedKeys()){
                        if (sk.isReadable()) {
                            SocketChannel sc =(SocketChannel) sk.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            sc.read(buffer);
                            buffer.flip();
                            String receivedString  = Charset.forName("UTF-8").newDecoder().decode(buffer).toString();
                            System.out.println("接受到来自服务端："+sc.socket().getRemoteSocketAddress()+" 的信息："+receivedString);
                            sk.interestOps(SelectionKey.OP_READ);
                        }
                        selector.selectedKeys().remove(sk);
                    }
                }
            }catch (IOException e){

            }
        }
    }
}

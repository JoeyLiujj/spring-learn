package cn.joey.nio;

import org.apache.ibatis.annotations.Select;
import org.bson.ByteBuf;
import org.springframework.expression.spel.ast.Selection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @auther liujiji
 * @date 2018/11/12 16:57
 */
public class SelectorConnection {
    private static final int BUF_SIZE = 1024;
    private static final int PORT =8000;
    private static final int TIMEOUT=3000;
    public static void main(String[] args){
        try {
            selector();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void handleAccept(SelectionKey key) throws IOException {
        ServerSocketChannel ssChannel = (ServerSocketChannel)key.channel();
        SocketChannel accept = ssChannel.accept();
        accept.configureBlocking(false);
        accept.register(key.selector(), SelectionKey.OP_READ,ByteBuffer.allocateDirect(BUF_SIZE));
    }

    public static void handleRead(SelectionKey key){
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer buf = (ByteBuffer) key.attachment();
        try {
            System.out.println("通道可读了");
            long bytesRead = sc.read(buf);
            while(bytesRead>0){
                buf.flip();
                while(buf.hasRemaining()){
                    System.out.println((char)buf.get());
                }
                buf.clear();
                bytesRead = sc.read(buf);
            }
            if(bytesRead==-1){
                sc.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void handleWrite(SelectionKey key) throws IOException {
        ByteBuffer buf = (ByteBuffer)key.attachment();
        buf.flip();
        System.out.println("通道可写了");
        SocketChannel sc = (SocketChannel) key.channel();
        while (buf.hasRemaining()) {
            sc.write(buf);
        }
        buf.compact();
    }



    private static void selector() throws IOException {
        Selector selector = null;
        ServerSocketChannel ssc= null;
        int channelCount = 0;
        try {
            selector = Selector.open();
            ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress(PORT));
            ssc.configureBlocking(false);
            ssc.register(selector,SelectionKey.OP_ACCEPT);
            while(true){
                if((channelCount = selector.select(TIMEOUT) ) == 0){
                    System.out.println("==");
                    continue;
                }
                System.out.println("打印注册的通道数："+channelCount);
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while(iter.hasNext()){
                    SelectionKey key = iter.next();
                    //个人认为此处只有服务器的ServerSocketChannel会有acceptable事件
                    //TODO 事实上不是如此，应该是服务端接受到客户端的请求后会触发该事件
                    if(key.isAcceptable()){
                        handleAccept(key);
                        //会去接受客户端的连接，并将客户端的通道注册到selector上
                    }
                    if (key.isReadable()) {
                        handleRead(key);
                    }
                    if (key.isWritable()) {
                        handleWrite(key);
                    }
                    if (key.isConnectable()) {
                        System.out.println("isConnectable = true");
                    }
                    //第一次的话，会移除服务端的ServerSocketChannel通道，把客户端的通道全部留下
                    //然后对客户端的通道进行判断读和写，如果可读则处理读事件，如果可写则处理写事件
                    iter.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (selector != null) {
                try {
                    selector.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ssc != null) {
                ssc.close();
            }
        }

    }


    public static void method1() {
        RandomAccessFile fromFile;
        RandomAccessFile toFile;
        try {
            fromFile = new RandomAccessFile("src/fromFile.xml","rw");
            FileChannel fromChannel = fromFile.getChannel();
            toFile = new RandomAccessFile("src/toFile.xml","rw");
            FileChannel toChannel = toFile.getChannel();
            long position =0;
            long count = fromChannel.size();
            System.out.println(count);
            toChannel.transferFrom(fromChannel, position, count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

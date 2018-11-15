package cn.joey.socket;

import com.mysql.fabric.Server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyServerNIO {
    private final static Logger logger = Logger.getLogger(MyServerNIO.class.getName());
    public static void main(String[] args) {
        Selector selector = null;
        ServerSocketChannel serverSocketChannel =null;

        try {
            //Selector for incoming time requests
            selector = Selector.open();

            //Create a new server socket and set to non blocking mode
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);

            //bind the server socket to the local host and port
            serverSocketChannel.socket().setReuseAddress(true);
            serverSocketChannel.socket().bind(new InetSocketAddress(10000));

            //Register accepts on the server socket with the selector. This step
            //tells the selector that the socket wants to be put on the ready
            //list when accept operations occur,so allowing multiplexed non-blocking
            //I/O to take place
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            //Here's where everything happens. The select method will return when any
            //operations registered above have occurred,the thread has been interrupted,etc
            while(selector.select()>0){
                //Someone is ready for I/O, get the ready keys
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();

                //Walk through the ready keys collection and process data requests
                while(it.hasNext()){
                    SelectionKey readKey = it.next();
                    it.remove();

                    // The key indexes into the selector so you
                    // can retrieve the socket that's ready for I/O
                    execute((ServerSocketChannel)readKey.channel());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                selector.close();
                serverSocketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static void execute(ServerSocketChannel serverSocketChannel){
        SocketChannel socketChannel=null;

        try {
            socketChannel = serverSocketChannel.accept();
            MyRequestObject myRequestObject = receiveData(socketChannel);
            logger.log(Level.INFO,myRequestObject.toString());

            MyResponseObject myResponseObject = new MyResponseObject("response for "+myRequestObject.getName(),"response for "+myRequestObject.getValue());
            sendData(socketChannel,myResponseObject);
            logger.log(Level.INFO,myResponseObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static MyRequestObject receiveData(SocketChannel socketChannel) throws IOException {
        MyRequestObject myRequestObject;
        ByteArrayOutputStream bAOS = new ByteArrayOutputStream();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        try{
            byte[] bytes;
            int size;
            //从通道中将数据放入到缓存区buffer中
            while ((size = socketChannel.read(buffer)) >= 0) {
                buffer.flip();
                bytes = new byte[size];
                //从缓冲中将数据放入到bytes字节数组中
                buffer.get(bytes);
                //这么写的做法是什么含义   理解为是将bytes数据写到输出流中，
                //最后再统一将输出流中的数据写出
                bAOS.write(bytes);
                buffer.clear();
            }
            bytes = bAOS.toByteArray();
            Object obj = SerializableUtil.toObject(bytes);
            myRequestObject = (MyRequestObject) obj;
        }finally {
            try {
                bAOS.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return myRequestObject;
    }

    private static void sendData(SocketChannel socketChannel, MyResponseObject myResponseObject) throws IOException {
        byte[] bytes = SerializableUtil.toBytes(myResponseObject);
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        socketChannel.write(buffer);
    }
}

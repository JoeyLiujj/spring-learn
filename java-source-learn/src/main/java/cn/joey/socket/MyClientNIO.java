package cn.joey.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyClientNIO {
    private static final Logger logger = Logger.getLogger(MyClient.class.getName());

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            final int idx = i;
            new Thread(new MyRunnable(idx)).start();
        }
    }
    private static final class MyRunnable implements Runnable{

        private final int idx;

        private MyRunnable(int idx){
            this.idx = idx;
        }
        @Override
        public void run() {
            SocketChannel socketChannel=null;
            try {
                socketChannel = SocketChannel.open();
                SocketAddress socketAddress = new InetSocketAddress("localhost",10000);
                socketChannel.connect(socketAddress);

                MyRequestObject myRequestObject = new MyRequestObject("request_"+idx,"request_"+idx);
                logger.log(Level.INFO,myRequestObject.toString());
                sendData(socketChannel, myRequestObject);

                MyResponseObject myResponseObject = receiveData(socketChannel);
                logger.log(Level.INFO,myResponseObject.toString());

            } catch (IOException e) {
                logger.log(Level.SEVERE,null,e);
            }finally {
                try {
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        private MyResponseObject receiveData(SocketChannel socketChannel) throws IOException {
            MyResponseObject myResponseObject;
            ByteArrayOutputStream BAOs = new ByteArrayOutputStream();

            try{
                ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
                byte[] bytes;
                int count;
                while((count=socketChannel.read(buffer))>=0){
                    // 此处为nio的Buffer的方法，讲解其作用
                    buffer.flip();
                    bytes = new byte[count];
                    buffer.get(bytes);
                    BAOs.write(bytes);
                    buffer.clear();
                }
                bytes = BAOs.toByteArray();
                Object obj = SerializableUtil.toObject(bytes);
                myResponseObject  = (MyResponseObject)obj;
                socketChannel.socket().shutdownInput();
            }finally {
                try {
                    BAOs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return myResponseObject;
        }

        private void sendData(SocketChannel socketChannel, MyRequestObject myRequestObject) throws IOException {
            byte[] bytes=SerializableUtil.toBytes(myRequestObject);
            ByteBuffer buffer = ByteBuffer.wrap(bytes);
            socketChannel.write(buffer);
            socketChannel.socket().shutdownOutput();
        }
        
    }
}

package cn.joey.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @auther liujiji
 * @date 2018/11/28 11:14
 */
public class NewIo {
    public static void main(String[] args) throws IOException {
        a();
    }

    public static void test(){
        IntBuffer buffer = IntBuffer.allocate(8);
        for (int i = 0; i < 5; i++) {
            int j = 2 *(i+1);
            buffer.put(j);
        }

        System.out.println("flip前limit的位置："+buffer.limit());
        System.out.println("flip前capacity的位置："+buffer.capacity());
        System.out.println("flip前position的位置："+buffer.position());
        buffer.flip();
        System.out.println("flip后limit的位置："+buffer.limit());
        System.out.println("flip后capacity的位置："+buffer.capacity());
        System.out.println("flip后position的位置："+buffer.position());

        while (buffer.hasRemaining()) {
            int j=buffer.get();
            System.out.print(j+" ");
        }
    }


    public static void testByteBuffer(){
        FileOutputStream fos=null;
        try {
            fos = new FileOutputStream("D:\\test.txt");
            FileChannel fc = fos.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(16);
            byte[] message = {1,2,3,4,5,6,7,8,9,0,10,11,12,13,14,15};
            for(int i=0;i<message.length;i++){
                buffer.put(message[i]);
            }

            buffer.flip();
            fc.write(buffer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void testByteBufferFlip(){
        RandomAccessFile file;
        try {
            file = new RandomAccessFile("C:\\Users\\liujiji\\Desktop\\新契约签单流程.txt","rw");
            FileChannel channel = file.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(48);

            int bytesRead = channel.read(buf);

            while(bytesRead!=-1){

                System.out.println("打印读取到的字节："+bytesRead);

                buf.flip();
                while (buf.hasRemaining()) {
                    System.out.println(buf.get());
                }

                buf.clear();

                bytesRead = channel.read(buf);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void a() throws IOException {
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(true);
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);

        while(true){
            int readyChannels = selector.select();
            if(readyChannels==0) continue;
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator iterator = selectionKeys.iterator();
            while(iterator.hasNext()){
                SelectionKey selectionKey = (SelectionKey) iterator.next();

                if(selectionKey.isWritable()){
                    //处理写事件
                }
                if (selectionKey.isReadable()) {
                    //处理读事件
                }
                if (selectionKey.isAcceptable()) {
                    //处理接收事件
                }

                if (selectionKey.isConnectable()) {

                }
                iterator.remove();
            }
        }

    }
}

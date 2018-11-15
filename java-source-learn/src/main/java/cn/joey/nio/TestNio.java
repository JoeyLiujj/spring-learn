package cn.joey.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author liujiji
 */
public class TestNio {
    public static void main(String[] args) throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("D:/balance/20180829/4872.txt", "rw");
        RandomAccessFile toFile = new RandomAccessFile("E:/balance/20180829/4872.txt", "rw");

        //获取channel
        FileChannel fromChannel = fromFile.getChannel();
        FileChannel toChannel = toFile.getChannel();

        int bufSize = 1024 * 4;

        //定义缓冲
        ByteBuffer byteBuffer = ByteBuffer.allocate(bufSize);

        int len;
        while ((len = fromChannel.read(byteBuffer)) != -1) {
            //切换到读模式
            byteBuffer.flip();

            //读取缓冲区数据写到目标通道中
            while (byteBuffer.hasRemaining()) {
                toChannel.write(byteBuffer);
            }
            System.out.println("每次读取的长度是：" + len);
            //清空缓存
            byteBuffer.clear();
        }
        //释放资源
        toChannel.close();
        fromChannel.close();
    }
}

package cn.joey.resume;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

public class FileUploadClient extends Socket{
    private Logger logger = LoggerFactory.getLogger("oaLogger");

    private Socket client;
    private static long status=0;
    private boolean quit =false;

    public FileUploadClient(String ip,Integer report) throws UnknownHostException,IOException{
        super(ip,report);
        this.client = this;
        if(client.getLocalPort()>0){
            System.out.println("Client [port"+client.getLocalPort()+"] 成功连接服务器");
        }else{
            System.out.println("服务器连接失败");
        }
    }

    public int sendFile(String filePath){
        DataOutputStream dos=null;
        DataInputStream dis = null;
        Long serverLength = -1L; //存储在服务器的输入流,默认为-1
        FileInputStream fis = null; //读取文件：输入流

        //获取：上传文件
        File file = new File(filePath);

        //  ==================== 节点：文件是否存在 ====================
        if(!file.exists()){
            try {
                //  发送：文件名称、文件长度
                dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                logger.error("Socket客户端：1.读取输入流发生错误");
                e.printStackTrace();
            }
            try {
                dos.writeUTF(file.getName());
                dos.flush();
                dos.writeLong(file.length());
                dos.flush();
            } catch (IOException e) {
                logger.error("Socket客户端：2.向服务器发送文件名、长度发生错误");
                e.printStackTrace();
            }
            //  获取：已上传文件长度
            try {
                dis = new DataInputStream(client.getInputStream());
            } catch (IOException e) {
                logger.error("Socket客户端：3.向服务器发送文件名、长度发生错误");
                e.printStackTrace();
            }
            while(serverLength==-1){
                try {
                    serverLength = dis.readLong();
                } catch (IOException e) {
                    logger.error("Socket客户端：4.读取服务器长度发送错误");
                }
            }

            //读取：需要上传的文件
            try {
                fis = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                logger.error("客户端：5.读取本地需要上传的文件失败，请确认文件是否存在");
                e.printStackTrace();
            }
            //发送 向服务器传输输入流
            try {
                dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                logger.error("Socket客户端：6.向服务器传输输入流发生错误");
            }

            System.out.println("==========开始传输文件================");
            byte[] bytes = new byte[1024];
            int length = 1024;

            long progress = serverLength;

            //设置游标：文件读取的位置
            if(serverLength ==-1){
                serverLength=0L;
            }
            try {
                fis.skip(serverLength);
            } catch (IOException e) {
                logger.error("Socket客户端：7.设置游标位置发生错误，请确认文件流是否被篡改");
                e.printStackTrace();
            }

            try {
                while (((length = fis.read(bytes, 0, bytes.length)) != -1) && quit != true) {
                    dos.write(bytes,0,length);
                    dos.flush();
                    progress+=length;
                    status  = (100*progress/file.length());
                }
            }catch (IOException e){
                logger.error("Socket客户端：8.设置游标位置发生错误，请确认文件流是否被篡改");
            }finally {
                if(fis!=null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        logger.error("Socket客户端：9.关闭读取的输入流异常");
                        e.printStackTrace();
                    }
                }

                if(dos!=null) {
                    try {
                        dos.close();
                    } catch (IOException e) {
                        logger.error("Socket客户端：10.关闭发送的输出流异常");
                        e.printStackTrace();
                    }
                }

                try {
                    client.close();
                } catch (IOException e) {
                    logger.error("Socket客户端：11.关闭客户端异常");
                    e.printStackTrace();
                }
            }
            System.out.println("=========文件传输成功============");
        }else{
            logger.error("Socket客户端：0.文件不存在");
            return -1;
        }
        return -1;
    }

    public void statusInfo(){
        Timer time  =new Timer();
        time.schedule(new TimerTask() {
            long num=0;
            @Override
            public void run() {
                if(status>num){
                    System.out.println("当前进度为："+status+"%");
                }

                if(status == 101){
                    System.gc();
                }
            }
        },0,100);
    }

    public void quit(){
        this.quit = true;
        try {
            this.close();
        } catch (IOException e) {
            System.out.println("服务器关闭发生异常，原因未知");
        }
    }
}

package cn.joey.resume;

import java.io.*;
import java.math.RoundingMode;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;

public class FileUploadServer extends ServerSocket {

    private static DecimalFormat df = null;
    private boolean quit = false;

    static {
        df = new DecimalFormat("#0.0");
        df.setRoundingMode(RoundingMode.HALF_UP);
        df.setMinimumFractionDigits(1);
        df.setMaximumFractionDigits(1);
    }

    public FileUploadServer(int report) throws IOException {
        super(report);
    }

    public void load() throws Exception {
        System.out.println("文件上传服务器：" + this.getInetAddress() + "正在上传中...");
        while (!quit) {
            // server尝试接受其他socket的连接请求，server的accpet方法时阻塞式的
            Socket socket = this.accept();

            String ip = socket.getInetAddress().toString();
            ip = ip.substring(1, ip.length());
            System.out.println("服务器接收到请求，正在开启验证对方合法性ip：" + ip + "！");
            new Thread(new Task(socket, ip)).start();
        }
    }


    class Task implements Runnable {

        private Socket sk;//当前连接
        private String ips; //当前连接ip

        public Task(Socket socket, String ips) {
            this.sk = socket;
            this.ips = ips;
        }

        @Override
        public void run() {
            Socket socket = sk;
            String ip = ips;
            long serverLength = -1L;  //  定义：存放在服务器里的文件长度，默认没有为-1
            char pathChar = File.separatorChar;

            String panfu = "D:";
            DataInputStream dis = null;  //获取：客户端输出流
            DataOutputStream dos = null; //发送：向客户端输入流
            FileOutputStream fos = null;  //读取：服务器本地文件流
            RandomAccessFile randomAccessFile = null;  //操作类：随机读写

            try {
                dis = new DataInputStream(socket.getInputStream());

                dos = new DataOutputStream(socket.getOutputStream());
                //定义客户端传过来的文件名
                String fileName = "";
                while (fileName == "") {
                    fileName = dis.readUTF();
                    System.out.println("服务器获取客户端文件名称：" + fileName);
                    File file = new File(panfu + pathChar + "receive" + pathChar + "" + ip + pathChar + fileName);

                    if (file.exists()) {
                        serverLength = file.length();
                        dos.writeLong(serverLength);
                        System.out.println("向客户端返回文件长度：" + serverLength + " B");
                    } else {
                        serverLength = 0L;
                        dos.writeLong(serverLength);
                        System.out.println("文件不存在");
                        System.out.println("向客户端返回文件长度:" + serverLength + " B");
                    }
                }
                System.out.println("服务器建立新线程处理客户端请求，对方ip:" + ip + ", 传输正在进行中...");
                //从客户端获取输入流
                dis = new DataInputStream(socket.getInputStream());
                //文件名和长度
                long fileLength = dis.readLong();
                File directory = new File(panfu + pathChar + "receive" + pathChar + "" + ip + pathChar);

                if (!directory.exists()) {
                    directory.mkdirs();
                }
                int length = 0;
                byte[] bytes = new byte[1024];
                File file = new File(directory.getAbsolutePath() + pathChar + fileName);
                if (!file.exists()) {
                    fos = new FileOutputStream(file);
                    //开始接收文件
                    while ((length = dis.read(bytes, 0, bytes.length)) != -1) {
                        fos.write(bytes, 0, length);
                        fos.flush();
                    }
                } else {
                    //存储在服务器中的文件长度
                    long fileSize = file.length(), pointSize = 0;
                    //判断是否已下载完成
                    if (fileLength > fileSize) {
                        //断点下载
                        pointSize = fileSize;
                    } else {
                        //重新下载
                        file.delete();
                        file.createNewFile();
                    }
                    randomAccessFile = new RandomAccessFile(file, "rw");
                    randomAccessFile.seek(pointSize);
                    while ((length = dis.read(bytes, 0, bytes.length)) != -1) {
                        randomAccessFile.write(bytes, 0, length);
                    }
                }
                System.out.println("文件接收成功 [file Name:" + fileName + "] [[ClientIP:" + ip + "][Size: " + getFormatFileSize(file.length()) + "]=======");
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                    if (dis != null) {
                        dis.close();
                    }
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Socket关闭失败！");
                }
            }
        }
    }
    public String getFormatFileSize(long length) {
        double size = ((double) length) / (1 << 30);
        if (size >= 1) {
            return df.format(size) + "GB";
        }
        size = ((double) length) / (1 << 20);
        if (size >= 1) {
            return df.format(size) + "MB";
        }
        size = ((double) length) / (1 << 10);
        if (size >= 1) {
            return df.format(size) + "KB";
        }
        return size + "B";
    }

    public void quit() {
        this.quit = true;
        try {
            this.clone();
        } catch (Exception e) {
            System.out.println("服务器关闭发生异常，原因未知");
        }
    }
}

package cn.joey.enable;

import org.springframework.stereotype.Component;

@Component
public class FtpServer implements Server {
    @Override
    public void start() {
        System.out.println("FTP 服务器启动中。。。");
    }

    @Override
    public void end() {
        System.out.println("FTP 服务器关闭中。。。");
    }
}

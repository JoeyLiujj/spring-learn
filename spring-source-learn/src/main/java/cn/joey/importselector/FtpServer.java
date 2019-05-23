package cn.joey.importselector;

import org.springframework.stereotype.Component;

/**
 * @auther liujiji
 * @date 2019/5/23 10:24
 */
@Component
public class FtpServer implements Server {
    @Override
    public void start() {
        System.out.println("Ftp服务器启动中...");
    }

    @Override
    public void end() {
        System.out.println("Ftp服务器关闭中...");
    }
}

package cn.joey.importselector;

import org.springframework.stereotype.Component;

/**
 * @auther liujiji
 * @date 2019/5/23 10:23
 */
@Component
public class HttpServer implements Server {
    @Override
    public void start() {
        System.out.println("Http服务器启动中...");
    }

    @Override
    public void end() {
        System.out.println("Http服务器关闭中...");
    }
}

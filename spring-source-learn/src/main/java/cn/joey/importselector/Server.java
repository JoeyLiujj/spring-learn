package cn.joey.importselector;

/**
 * @auther liujiji
 * @date 2019/5/23 10:23
 */
public interface Server {
    void start();

    void end();

    enum Type {
        HTTP,FTP
    }
}

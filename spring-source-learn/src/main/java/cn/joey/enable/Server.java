package cn.joey.enable;

public interface Server {

    void start();
    void end();
    enum Type{
        HTTP,
        FTP
    }
}

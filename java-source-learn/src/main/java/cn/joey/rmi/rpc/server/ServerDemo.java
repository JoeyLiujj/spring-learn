package cn.joey.rmi.rpc.server;

import cn.joey.rmi.rpc.GPHello;

import java.io.IOException;

/**
 * @auther liujiji
 * @date 2019/1/9 15:27
 */
public class ServerDemo {
    public static void main(String[] args) {
        RpcServer rpcServer = new RpcServer();
        GPHello gpHello = new GPHelloImpl();
        try {
            rpcServer.publish(gpHello,8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

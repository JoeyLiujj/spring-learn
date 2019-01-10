package cn.joey.rmi.rpc;

import java.rmi.Remote;

/**
 * @auther liujiji
 * @date 2019/1/9 15:41
 */
public interface GPHello extends Remote {
    String sayHello(String msg);
}

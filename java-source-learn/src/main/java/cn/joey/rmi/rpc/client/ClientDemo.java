package cn.joey.rmi.rpc.client;

import cn.joey.rmi.rpc.GPHello;

/**
 * @auther liujiji
 * @date 2019/1/9 16:45
 */
public class ClientDemo {
    public static void main(String[] args) {
        GPHello gpHello = RpcClientProxy.clientProxy(GPHello.class,"localhost",8888);
        String mic = gpHello.sayHello("Mic");
        System.out.println(mic);
    }
}

package cn.joey.rmi.rpc.server;

import cn.joey.rmi.rpc.GPHello;

/**
 * @auther liujiji
 * @date 2019/1/9 15:42
 */
public class GPHelloImpl implements GPHello {
    @Override
    public String sayHello(String msg) {
        return "Hello,"+msg;
    }
}

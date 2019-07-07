package cn.joey.rpc.provider;

import cn.joey.rpc.api.IRpcHelloService;

/**
 * @auther liujiji
 * @date 2019/6/18 15:27
 */
public class RpcHelloServiceImpl implements IRpcHelloService {

    @Override
    public String sayHello(String name) {
        return "Hello "+name+"!";
    }
}

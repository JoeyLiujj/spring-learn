package cn.joey.rpc.consumer;

import cn.joey.rpc.api.IRpcHelloService;
import cn.joey.rpc.api.IRpcService;
import lombok.extern.slf4j.Slf4j;

/**
 * @auther liujiji
 * @date 2019/6/18 16:34
 */
@Slf4j
public class RpcConsumer {
    public static void main(String[] args) {
        IRpcHelloService rpcHello = RpcProxy.create(IRpcHelloService.class);
        log.info(rpcHello.sayHello("Joey"));

        IRpcService service = RpcProxy.create(IRpcService.class);
        log.info("8 + 2 = "+ service.add(8,2));
        log.info("8 - 2 = "+ service.sub(8,2));
        log.info("8 * 2 = "+ service.mult(8,2));
        log.info("8 / 2 = "+ service.div(8,2));
    }
}

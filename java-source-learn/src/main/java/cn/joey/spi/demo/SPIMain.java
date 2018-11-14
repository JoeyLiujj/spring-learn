package cn.joey.spi.demo;

import java.util.ServiceLoader;

/**
 * @auther liujiji
 * @date 2018/11/13 14:57
 */
public class SPIMain {
    public static void main(String[] args) {
        ServiceLoader<IHelloService> load = ServiceLoader.load(IHelloService.class);
        for (IHelloService impl : load) {
            impl.sayHello();
        }
    }
}

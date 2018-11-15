package cn.joey.spi.demo.impl;

import cn.joey.spi.demo.IHelloService;

/**
 * @auther liujiji
 * @date 2018/11/13 14:54
 */
public class FaceHelloServiceImpl implements IHelloService {
    @Override
    public void sayHello() {
        System.out.println("say hello with face:^_^");
    }
}

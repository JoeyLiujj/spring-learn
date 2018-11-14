package cn.joey.spi.demo.impl;

import cn.joey.spi.demo.IHelloService;

/**
 * @auther liujiji
 * @date 2018/11/13 14:54
 */
public class TextHelloServiceImpl implements IHelloService {
    @Override
    public void sayHello() {
        System.out.println("say hello with text：你好！");
    }
}

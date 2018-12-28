package cn.joey.spi.demo.impl;

import cn.joey.spi.demo.IHelloService;
import lombok.extern.slf4j.Slf4j;

/**
 * @auther liujiji
 * @date 2018/11/13 14:54
 */
@Slf4j
public class FaceHelloServiceImpl implements IHelloService {
    @Override
    public void sayHello() {
        log.info("say hello with face:^_^");
    }
}

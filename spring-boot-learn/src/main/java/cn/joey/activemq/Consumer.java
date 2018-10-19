package cn.joey.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

//    @JmsListener(destination = "myTest.queue")
//    注释掉  避免每次启动时都访问activemq连接
    public void receiveMessage(String text) {
        System.out.println("Consumer收到的报文为：" + text);
    }
}

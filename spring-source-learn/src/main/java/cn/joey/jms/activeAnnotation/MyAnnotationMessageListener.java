package cn.joey.jms.activeAnnotation;

import cn.joey.jms.jmsTemplate.User;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;


@Component
public class MyAnnotationMessageListener {
    @JmsListener(destination = "queue",concurrency = "5")
    public void handleMessage(User user,Message message) throws JMSException {
        System.out.println(message.getJMSMessageID()+"=="+user.getPassword()+"----"+user.getUsername());
        System.out.println("是否会调用此监听器");
    }
}

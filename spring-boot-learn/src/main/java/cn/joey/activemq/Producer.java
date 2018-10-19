package cn.joey.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Service("producer")
public class Producer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMessage(Destination destination,final String message){
        jmsMessagingTemplate.convertAndSend(destination,message);
    }

    public void sendMessage(String destinationName,final String message){
        jmsMessagingTemplate.convertAndSend(destinationName,message);
    }

//    @JmsListener(destination = "other.queue")
    public void receiverMessage(String text){
        System.out.println("重新接受从Producer发送的消息mytest.queue,再重新发送回来消息："+text);
    }
}

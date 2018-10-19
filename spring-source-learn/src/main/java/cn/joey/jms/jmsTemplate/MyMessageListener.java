package cn.joey.jms.jmsTemplate;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println(message);
        ActiveMQObjectMessage objectMessage = (ActiveMQObjectMessage)message;
        try {
            User user=(User)objectMessage.getObject();
            System.out.println(user.getUsername()+"----"+user.getPassword());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

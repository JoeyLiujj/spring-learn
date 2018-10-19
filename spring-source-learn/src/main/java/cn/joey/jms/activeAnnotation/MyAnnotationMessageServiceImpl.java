package cn.joey.jms.activeAnnotation;

import cn.joey.jms.jmsTemplate.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class MyAnnotationMessageServiceImpl implements MyAnnotationMessageService {
    @Autowired
    private JmsTemplate jmsAnnotationTemplate;

    @Override
    public void sendMessage(User user) {
        for(int i=0;i<10;i++){
            user.setUsername(user.getUsername()+"---"+i);
            jmsAnnotationTemplate.convertAndSend("queue",user);
        }
//        jmsAnnotationTemplate.send("queue", new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                return session.createObjectMessage(user);
//            }
//        });
    }
}

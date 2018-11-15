package cn.joey.jms.activeAnnotation;

import cn.joey.jms.jmsTemplate.User;

public interface MyAnnotationMessageService {
    void sendMessage(User user);
}

package cn.joey.jms.activemqSource;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;
import java.io.IOException;

public class JMSSourceSendTest {
    public static void main(String[] args)  throws JMSException{
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection conn = null;
        Session session =null;
        //类似于JDBC的初始代码，大部分代码是做重复性的工作 比如获取连接，创建session、异常处理。
        try {
            conn = connectionFactory.createConnection();
            conn.start();
            session  = conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
            Destination destination = new ActiveMQQueue("spinner.queue");
            MessageProducer producer = session.createProducer(destination);
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText("Hello World");
            producer.send(textMessage);
        }finally {
            if (session != null) {
                session.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}

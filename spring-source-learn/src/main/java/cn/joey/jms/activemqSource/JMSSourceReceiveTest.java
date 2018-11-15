package cn.joey.jms.activemqSource;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;
import java.io.IOException;

public class JMSSourceReceiveTest {
    public static void main(String[] args) throws JMSException, IOException{
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection conn = null;
        Session session =null;
        //类似于JDBC的初始代码，大部分代码是做重复性的工作 比如获取连接，创建session、异常处理。
        try {
            conn = connectionFactory.createConnection();
            conn.start();
            session  = conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
            Destination destination = new ActiveMQQueue("spinner.queue");
            MessageConsumer consumer = session.createConsumer(destination);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    System.out.println(message);
                }
            });
            System.in.read();
        } finally {
            if (session != null) {
                session.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}

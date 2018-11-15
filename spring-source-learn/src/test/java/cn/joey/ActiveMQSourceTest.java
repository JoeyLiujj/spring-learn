package cn.joey;

import cn.joey.jms.activeAnnotation.MyAnnotationMessageService;
import cn.joey.jms.jmsTemplate.MyMessageService;
import cn.joey.jms.jmsTemplate.User;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.*;
import java.io.IOException;

/**
 * JMS是一种与厂商无关的 API，用来访问消息收发系统消息。
 * 它类似于JDBC(Java Database Connectivity)，提供了应用程序之间异步通信的功能
 */

public class ActiveMQSourceTest {
    @Test
    public void testQueueProducer() throws JMSException {
        //第一步：创建ConnectionFactory对象，需要指定服务端ip及端口号
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        //第二步：使用ConnectionFactory对象创建一个Connection对象
        Connection connection = connectionFactory.createConnection();
        //第三步：开启连接，调用connection对象的start方法
        connection.start();
        //第四步：使用Connection对象创建一个Session对象
        //第一个参数：是否开启事务，true:开启事务，第二个参数忽略
        //第二个参数：当第一个参数为false时，才有意义。消息的对答模式，1、自动应答2、手动应答。一般是自动应答
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //第五步：使用session对象创建一个Destination对象(Queue,topic),此处创建一个queue对象
        Queue queue = session.createQueue("queue-profile");
        //第六步：使用session对象创建一个Producer对象
        MessageProducer producer = session.createProducer(queue);
        //第七步：创建一个Message对象，
        TextMessage message = session.createTextMessage("Hello activeMQ,this is my fisrt profile");
        //第八步：使用producer对象发送消息
        producer.send(message);
        //第九步：关闭资源
        producer.close();
        session.close();
        connection.close();
    }

    @Test
    public void testQueueConsumer() throws JMSException, IOException {
        //第一步：创建ConnectionFactory对象，需要指定服务端ip及端口号
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        //第二步：使用ConnectionFactory对象创建一个Connection对象
        Connection connection = connectionFactory.createConnection();
        //第三步：开启连接，调用connection对象的start方法
        connection.start();
        //第四步：使用Connection对象创建一个Session对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //第五步：使用session对象创建一个Destination对象(Queue,topic),此处创建一个queue对象
        Queue queue = session.createQueue("queue-profile");
        //第六步：使用session创建一个Consumer对象
        MessageConsumer consumer = session.createConsumer(queue);
        //第七步：接受消息
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                String text;
                try {
                    text = textMessage.getText();
                    //第八步：打印消息
                    System.out.println(text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //目的就是为了不让该方法结束，或者说是不让该线程死亡
        //while(true){
        //    TextMessage textMessage=(TextMessage) consumer.receive(10000);
        //    if(textMessage!=null){
        //        System.out.println(textMessage.getText());
        //    }else{
        //        break;
        //    }
        //}

        //键盘输入
        // 我的理解是模拟应用实时启动，当有消息发送到queue-test目的地时，消费者能随时对消息进行消费
        System.in.read();
        //第九步：关闭资源
        consumer.close();
        session.close();
        connection.close();
    }

    @Test
    public void testTopicProducer() throws JMSException {
        //第一步：创建ConnectionFactory对象，需要指定服务端ip及端口号
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        //第二步：使用ConnectionFactory对象创建一个Connection对象
        Connection connection = connectionFactory.createConnection();
        //第三步：开启连接，调用connection对象的start方法
        connection.start();
        //第四步：使用Connection对象创建一个Session对象
        //第一个参数：是否开启事务，true:开启事务，第二个参数忽略
        //第二个参数：当第一个参数为false时，才有意义。消息的对答模式，1、自动应答2、手动应答。一般是自动应答
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //第五步：使用session对象创建一个Destination对象(Queue,topic),此处创建一个queue对象
        Topic topic = session.createTopic("queue-profile");
        //第六步：使用session对象创建一个Producer对象
        MessageProducer producer = session.createProducer(topic);
        //第七步：创建一个Message对象，
        TextMessage message = session.createTextMessage("Hello activeMQ,this is my fisrt profile");
        //第八步：使用producer对象发送消息
        producer.send(message);
        //第九步：关闭资源
        producer.close();
        session.close();
        connection.close();
    }

    @Test
    public void testTopicConsumer1() throws JMSException, IOException {
        //第一步：创建ConnectionFactory对象，需要指定服务端ip及端口号
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        //第二步：使用ConnectionFactory对象创建一个Connection对象
        Connection connection = connectionFactory.createConnection();
        //第三步：开启连接，调用connection对象的start方法
        connection.start();
        //第四步：使用Connection对象创建一个Session对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //第五步：使用session对象创建一个Destination对象(Queue,topic),此处创建一个queue对象
        Topic topic = session.createTopic("queue-profile");
        //第六步：使用session创建一个Consumer对象
        MessageConsumer consumer = session.createConsumer(topic);
        //第七步：接受消息
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                String text;
                try {
                    text = textMessage.getText();
                    //第八步：打印消息
                    System.out.println(message + "-----" + text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //键盘输入
        // 我的理解是模拟应用实时启动，当有消息发送到queue-test目的地时，消费者能随时对消息进行消费
        System.in.read();
        //第九步：关闭资源
        consumer.close();
        session.close();
        connection.close();
    }

    @Test
    public void testTopicConsumer2() throws JMSException, IOException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("queue-profile");
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                String text;
                try {
                    text = textMessage.getText();
                    //第八步：打印消息
                    System.out.println(message + "----" + text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }

    @Test
    public void testJMSSendTemplate() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("activemq.xml");
        MyMessageService service = (MyMessageService) context.getBean("myMessageService");
        User user = new User();
        user.setPassword("1234wei");
        user.setUsername("Joey");
        service.sendMessage(user);
    }
    @Test
    public void testJMSReceiveTemplate() throws IOException {
        new ClassPathXmlApplicationContext("activemq.xml");
        System.in.read();
    }

    @Test
    public void testAnnotationJMSTemplate() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("activemqAnnotation.xml");
        MyAnnotationMessageService service = (MyAnnotationMessageService)context.getBean("myAnnotationMessageServiceImpl");
        User user = new User();
        user.setPassword("1q2w3e4r");
        user.setUsername("Joey");
        service.sendMessage(user);
    }
}

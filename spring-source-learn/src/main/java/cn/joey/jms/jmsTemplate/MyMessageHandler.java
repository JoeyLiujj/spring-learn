package cn.joey.jms.jmsTemplate;

public class MyMessageHandler {
    public void handleMessage(User user){
        System.out.println(user.getPassword()+"----"+user.getUsername());
    }
}

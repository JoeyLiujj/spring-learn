package cn.joey.mediator;

/**
 * @auther liujiji
 * @date 2018/10/19 14:16
 * 租房者
 */
public class Tenant extends Person {

    public Tenant(String name, Mediator mediator) {
        super(name, mediator);
    }
    public void constact(String message) {
        mediator.constact(message,this);
    }

    public void getMessage(String message) {
        System.out.println("租房者："+name+",获得信息："+message);
    }
}

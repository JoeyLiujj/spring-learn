package cn.joey;

import org.springframework.context.support.GenericApplicationContext;

public class ApplicationListenerBeanOnCustomEventBootstrap {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        context.registerBean(MyApplicationListener.class);
        context.refresh();

        context.publishEvent(new MyApplicationEvent("Hello World!"));

        context.close();

        context.publishEvent(new MyApplicationEvent("Hello world again"));
    }
}

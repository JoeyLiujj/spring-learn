package cn.joey;

import org.springframework.context.ApplicationListener;

public class MyApplicationListener implements ApplicationListener<MyApplicationEvent>{
    @Override
    public void onApplicationEvent(MyApplicationEvent event) {
        System.out.println(event.getClass().getSimpleName());
    }
}

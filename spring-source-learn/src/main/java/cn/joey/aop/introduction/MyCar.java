package cn.joey.aop.introduction;

import org.springframework.stereotype.Component;

//@Component
public class MyCar implements Auto {
    public void driving(){
        System.out.println("开车了");
    }
}

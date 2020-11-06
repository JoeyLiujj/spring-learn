package cn.joey.circle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class A {
//    @Autowired
//    public A(B b){
//
//    }
    private B b;
    @Autowired
    public void setB(B b){

    }
}

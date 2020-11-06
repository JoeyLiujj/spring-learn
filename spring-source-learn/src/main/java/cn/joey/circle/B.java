package cn.joey.circle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class B {
//    @Autowired
//    public B(C c){
//
//    }

    private C c;
    @Autowired
    public void setB(C c){

    }
}

package cn.joey.circle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class C {
//    @Autowired
//    public C(A a) {
//
//    }

    private A a;
    @Autowired
    public void setB(A a){

    }
}

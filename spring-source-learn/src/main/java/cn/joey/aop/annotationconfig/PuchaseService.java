package cn.joey.aop.annotationconfig;

import cn.joey.aop.introduction.MyCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PuchaseService {

    @Autowired
    MyCar cara;

    public String purchaseProduct(String productName, int price, String type) {
        System.out.println("购买商品。。。");
        cara.driving();
//        testIfCanProxy();
        return "zhangsan";
    }

    public void testIfCanProxy(){
        System.out.println("查看该类是否会被代理");
    }
}

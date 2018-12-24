package cn.joey.aop.annotationconfig;

import org.springframework.stereotype.Component;

@Component
public class PuchaseService {
    public String purchaseProduct(String productName, int price, String type) {
        System.out.println("购买商品。。。");
        testIfCanProxy();
        return "zhangsan";
    }

    public void testIfCanProxy(){
        System.out.println("查看该类是否会被代理");
    }
}

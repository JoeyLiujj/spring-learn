package cn.joey.aop.annotationconfig;

import org.springframework.stereotype.Component;

@Component
public class PuchaseService {
    public String purchaseProduct(String productName, int price, String type) {
        System.out.println("购买商品。。。");
        return "zhangsan";
    }
}

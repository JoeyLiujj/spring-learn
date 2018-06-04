package cn.joey.aop;

import org.springframework.stereotype.Component;

@Component
public class PuchaseService {
    public String purchaseProduct(String productName, int price, String type) throws Exception{
        System.out.println("购买商品。。。");
        int i=1/0;
        return "zhangsan";
    }
}

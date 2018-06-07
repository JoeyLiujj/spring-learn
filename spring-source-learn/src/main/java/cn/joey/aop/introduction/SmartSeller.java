package cn.joey.aop.introduction;

public class SmartSeller implements Seller {
    public void sell(String goods, String clientName) {
        System.out.println("Smart Sell"+goods+" to "+clientName+"...");
    }
}

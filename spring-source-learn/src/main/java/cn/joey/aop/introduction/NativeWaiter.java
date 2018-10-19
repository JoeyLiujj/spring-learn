package cn.joey.aop.introduction;

public class NativeWaiter implements Waiter {
    public void greetTo(String clientName) {
        System.out.println("NativeWaiter:greet to"+clientName+"...");
    }

    public void serveTo(String clientName) {
        System.out.println("NativeWaiter:serve to"+clientName+"...");
    }
}

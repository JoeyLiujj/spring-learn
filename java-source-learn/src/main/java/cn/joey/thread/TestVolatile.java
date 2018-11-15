package cn.joey.thread;

import java.util.concurrent.TimeUnit;

public class TestVolatile {
    static volatile boolean isStop = false;
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            int i=0;
            @Override
            public void run() {
                while(!isStop){
                    i++;
                    System.out.println(i);
                }
            }
        });
        thread.start();
        Thread.sleep(20);
        isStop=true;
    }
}

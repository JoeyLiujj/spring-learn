package cn.joey;

import cn.joey.spi.demo.impl.TextHelloServiceImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(14);
        for (int i = 0; i < 60; i++) {
            es.execute(new Runnable() {
                @Override
                public void run() {
                    for(int i=0;i<100000;i++){
                    }
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("当前执行的线程是："+Thread.currentThread().getName());
                }
            });
        }

        es.shutdown();
    }
}

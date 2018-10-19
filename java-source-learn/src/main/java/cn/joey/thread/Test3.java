package cn.joey.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Test3 {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);

        MyRunnerForReentrantLock run1 = new MyRunnerForReentrantLock();
        MyRunnerReentrantRWLock run = new MyRunnerReentrantRWLock();

        for(int i=0;i<10;i++) {
            sleep(1000);
//            pool.execute(run1);
            pool.execute(run);
        }
    }
    private static void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package cn.joey.thread;

import java.util.concurrent.Semaphore;

public class Semap implements Runnable {
    Semaphore semaphore = new Semaphore(1);
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + "---请求资源");
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName()+"---获取临界区资源");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName()+"---释放资源");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }
}

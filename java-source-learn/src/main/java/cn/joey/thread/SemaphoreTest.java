package cn.joey.thread;

import java.util.concurrent.Semaphore;

/**
 * @auther liujiji
 * @date 2018/11/19 16:07
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        int n=8;
        Semaphore semaphore = new Semaphore(5);
        for (int i = 1; i <= n; i++) {
            new Worker(i,semaphore).start();
        }
    }
    static class Worker extends Thread{
        private int num;
        private Semaphore semaphore;

        public Worker(int num ,Semaphore semaphore){
            this.num = num;
            this.semaphore = semaphore;
        }
        @Override
        public void run() {
            try{
                semaphore.acquire();
                System.out.println("工人"+this.num+"占用一个机器在生产。。。");
                Thread.sleep(1000);
                semaphore.release();
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {

            }
        }
    }
}

package cn.joey.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierDemo {
    private final static int SUM=3;

    public static void main(String[] args) {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(SUM, new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"  执行Barrier自带的任务");
            }
        });
        for (int i = 0; i <SUM ; i++) {
            new Task(cyclicBarrier).start();
        }
    }

    private static class Task extends Thread{
        private CyclicBarrier cyclicBarrier;

        public Task(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier=cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"  初始化开始......");

            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName()+"   初始化结束，等待其他task初始化");
                //调用CyclicBarrier的await方法，---->即调用dowait方法
                //
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("其他Task初始化结束，开始执行！");
        }
    }
}

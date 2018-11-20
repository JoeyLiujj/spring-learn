package cn.joey.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;

/**
 * @auther liujiji
 * @date 2018/11/19 16:18
 */
public class CalculateFourDiskData {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
        Integer sum=0;
        for(int i=1;i<=4;i++){
            Thread thread = new Worker(i,cyclicBarrier);
            thread.start();
        }

        System.out.println("最后计算的四个盘符的总共是："+sum);
    }

    static class Worker extends Thread {

        private int i;
        private CyclicBarrier cyclicBarrier;
        public Worker(int i,CyclicBarrier cyclicBarrier){
            this.i=i;
            this.cyclicBarrier = cyclicBarrier;
        }
        @Override
        public void run() {
            char a = (char) Character.toLowerCase(i+48);
            System.out.println("开始计算"+a+":盘符的大小");
            try {
                Thread.sleep(5000); //计算中
                System.out.println("计算出的大小为："+(i+100));
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}


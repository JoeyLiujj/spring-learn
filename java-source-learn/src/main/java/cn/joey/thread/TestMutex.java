package cn.joey.thread;

import cn.joey.socket.User;

import java.lang.reflect.Field;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @auther Joey
 * @date 2018/11/14 10:58
 */
public class TestMutex {
    public static void main(String[] args) throws InterruptedException {
        String name = Thread.currentThread().getName();
        final User user = new User(name,"Mainpassword");
        for(int i=0;i<3;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 3; j++) {
                        user.setName(Thread.currentThread().getName());
                        user.setPassword(Thread.currentThread().getName()+j);
                        System.out.println("线程："+Thread.currentThread().getName()+"---"+j);
                    }
                }
            });
            thread.start();
        }

        Thread.sleep(2000);
        System.out.println(user.toString());
        System.out.println("主线程执行");
    }

}

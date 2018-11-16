package cn.joey.thread;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @auther liujiji
 * @date 2018/11/14 10:24
 */
public class UnParkAndParkTestDemo {
    public static void main(String[] args) throws InterruptedException {
        Unsafe unsafe = getUnsafe();
        final List<Thread> list= new ArrayList();

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"我被park住了");
                    list.add(Thread.currentThread());
                    LockSupport.park(Thread.currentThread());
                    System.out.println(Thread.currentThread().getName()+"---再次开始执行");
                }
            }).start();
        }

        Thread.sleep(2000);

        for (Thread thread:list) {
            System.out.println(thread.getName());
            unsafe.unpark(thread);
        }
        System.out.println("Success!!!!");
    }
    public static Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

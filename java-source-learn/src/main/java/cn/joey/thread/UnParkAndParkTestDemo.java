package cn.joey.thread;

import sun.misc.Unsafe;
import sun.plugin.security.ActivatorSecurityManager;

import java.lang.reflect.Field;

/**
 * @auther liujiji
 * @date 2018/11/14 10:24
 */
public class UnParkAndParkTestDemo {
    public static void main(String[] args) {
        Unsafe unsafe = getUnsafe();
        final Thread currThread = Thread.currentThread();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    currThread.interrupt();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        unsafe.park(false,0);
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

package cn.joey.thread;

import cn.joey.socket.User;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @auther liujiji
 * @date 2018/10/26 11:08
 * 本段代码演示了sleep不会释放锁的情况，在主线程和子线程之间切换
 */
public class TestThread {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService laodaA = Executors.newFixedThreadPool(7);
        String s = laodaA.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName());
                return "I am a task,which submited by the so called laoda,and run by those anonymous workers";
            }
        }).get();
        System.out.println(Thread.currentThread().getName());
        System.out.println(s);

        System.out.println(-1 << 29);
    }
}

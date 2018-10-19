package cn.joey.thread.condition;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestCondition {
    ReentrantLock lock = new ReentrantLock();
    ThreadLocal<AtomicInteger> threadLocal = new ThreadLocal<AtomicInteger>();
    Condition condition = lock.newCondition();

    public static void main(String[] args) {
        TestCondition testCondition  = new TestCondition();
        testCondition.testWait();
    }

    private void testWait(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set(new AtomicInteger(1));
                while(true){
                    if(threadLocal.get().getAndIncrement()==5){
                        System.out.println("signal----!!!");
                        try{
                            lock.lock();
                            condition.signal();
                        }finally {
                            lock.unlock();
                        }
                    }
                    System.out.println("thread---01");
                    sleep(1000);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set(new AtomicInteger(1));
                while (true) {
                    if(threadLocal.get().getAndIncrement()==2){
                        try {
                            lock.lock();//这里同样要加锁，否则会抛出IllegalMonitorStateException异常。注意的是这里不要使用synchronized进行加锁，而是使用lock
                            condition.await();
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }finally {
                            lock.unlock();
                        }
                    }
                    System.out.println("thread---02");
                    sleep(1000);
                }
            }
        }).start();
    }

    private void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

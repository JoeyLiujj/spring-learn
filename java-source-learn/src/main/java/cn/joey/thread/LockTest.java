package cn.joey.thread;

import java.io.Console;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockTest {
    ReentrantLock lock = new ReentrantLock();

    public void reEnterLock(AtomicInteger time) {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "--" + time);
        try {
            if (time.get() == 0) {
                return;
            } else {
                time.getAndDecrement();
                reEnterLock(time);
            }
        } finally {
            lock.unlock();
        }
    }
    ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(true);//公平读写锁

    public void read(){
        rwLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"---read");
            sleep(2000);
        }finally {
            rwLock.readLock().unlock();
        }
    }
    public void write(){
        rwLock.writeLock().lock();
        try{
            sleep(2000);
            System.out.println(Thread.currentThread().getName()+"---write");
        }finally {
            rwLock.writeLock().unlock();
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

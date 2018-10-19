package cn.joey.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyRunnerReentrantRWLock implements Runnable {
    LockTest lockTest = new LockTest();
    AtomicInteger num = new AtomicInteger(1);//用来切换读写锁测试方法
    @Override
    public void run() {
        if (num.getAndIncrement() == 4) {
            lockTest.write();//调用写锁操作
        }else{
            lockTest.read();//调用读锁操作
        }
    }

}

package cn.joey.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyRunnerForReentrantLock implements Runnable {
    LockTest lockTest = new LockTest();
    @Override
    public void run() {
        //在run方法中调用reEnterLock方法测试重入测试
        lockTest.reEnterLock(new AtomicInteger(3));
    }
}

package cn.joey.thread.deadLock;

import lombok.extern.slf4j.Slf4j;

/**
 * @auther liujiji
 * @date 2018/11/7 17:48
 */
@Slf4j
public class DeadLockDemo {

    Object object1 = new Object();
    Object object2 = new Object();

    public void method1() throws InterruptedException {
        synchronized (object1){
            Thread.sleep(3000);
            synchronized (object2){
                log.info("method1");
            }
        }
    }
    public void method2() throws InterruptedException {
        synchronized (object2){
            Thread.sleep(3000);
            synchronized (object1){
                log.info("method2");
            }
        }
    }
}

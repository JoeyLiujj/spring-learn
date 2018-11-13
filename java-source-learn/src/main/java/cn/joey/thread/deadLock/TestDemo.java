package cn.joey.thread.deadLock;

/**
 * @auther liujiji
 * @date 2018/11/7 17:50
 */
public class TestDemo {
    public static void main(String[] args) throws Exception{
        DeadLockDemo testDemo = new DeadLockDemo();
        Thread thread = new Thread(new DeadLockDemo1(testDemo));
        Thread thread1 = new Thread(new DeadLockDemo2(testDemo));
        thread.start();
        thread1.start();
    }

}
class DeadLockDemo1 implements Runnable{

    DeadLockDemo testDemo;
    public DeadLockDemo1(DeadLockDemo testDemo){
        this.testDemo = testDemo;
    }
    @Override
    public void run() {
        try {
            testDemo.method1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class DeadLockDemo2 implements Runnable{
    DeadLockDemo testDemo;
    public DeadLockDemo2(DeadLockDemo testDemo){
        this.testDemo = testDemo;
    }
    @Override
    public void run() {
        try {
            testDemo.method2();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

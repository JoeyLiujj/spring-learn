package cn.joey.thread;

public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 200; i++)
                    System.out.println("执行线程：" + Thread.currentThread().getName());
                }
        },"thread-01");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 200; i++)
                    System.out.println("执行线程：" + Thread.currentThread().getName());
            }
        },"thread-02");

        thread1.start();
        thread1.join();
        thread2.start();
//        thread2.join();
        System.out.println("我什么时候执行到");
    }
}

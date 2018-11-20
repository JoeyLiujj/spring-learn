package cn.joey.thread;

/**
 * @auther liujiji
 * @date 2018/11/16 9:42
 */
public class TestVolatileDemo {
    private static volatile int i=0;
    private static volatile int j=0;
    private static int x=0,y=0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                j=1;
                x=i;
            }
        });
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                i=1;
                y=j;
            }
        });

        thread1.start();
        thread.start();
        Thread.sleep(1000);
        System.out.println("x="+x+" ----> y="+y);
    }
}

package cn.joey.thread;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestVolatile {
    private static boolean isInitCompleted = false;
    private static String context ;
    public static void main(String[] args) throws InterruptedException {
//        Thread thread0 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName()+"--->"+isInitCompleted);
//                while(isInitCompleted){
//                    try {
//                        doSomethingWithConfig(context);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        System.out.println(e.getMessage());
//                    }
//                    return ;
//                }
//                //后续没办法使用context进行一系列操作
//            }
//        });
//        Thread thread1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                context = loadContext();
//                isInitCompleted=true;
//                //指令重排序导致isStop排到loadContext前面，这时会造成context没有被初始化，造成
//                //context还没被初始化，结果就是isStop为true，线程1开始执行，获取到的context是null，出现异常
//            }
//        });
//        thread1.start();
//        thread0.start();

//        Thread.sleep(1000);
//        Thread.interrupted();
        Thread.currentThread().interrupt();
//        Thread.currentThread().isInterrupted();
        System.out.println(Thread.currentThread().isInterrupted());
        System.out.println("还是执行了");
    }

    private static String loadContext() {
        context = "initMain";
        System.out.println(Thread.currentThread().getName()+"--->"+context);
        return context;
    }


    private static void doSomethingWithConfig(String context) throws IOException {
        System.out.println(Thread.currentThread().getName()+"--->"+context);
        if(context==null){
            throw new IOException("IO Exception");
        }
    }
}

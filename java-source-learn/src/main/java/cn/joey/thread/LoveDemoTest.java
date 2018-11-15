package cn.joey.thread;

public class LoveDemoTest {
    public static void main(String[] args) {
        // 创建数据对象
        WRFile wr = new WRFile();
        //设置和获取类
        FirstThread ft = new FirstThread(wr);
        SecondThread st = new SecondThread(wr);
        //线程类
        Thread thread1 = new Thread(ft);
        Thread thread2 = new Thread(st);
        thread1.start();
        thread2.start();
    }
}

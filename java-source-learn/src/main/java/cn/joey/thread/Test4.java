package cn.joey.thread;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test4 {
    public static void main(String[] args) throws InterruptedException {
        File file = new File("D:/balance/text.txt");
        Thread thread = new Thread(new FileThread(file,"往后余生，风雪是你"));
        Thread thread1= new Thread(new FileThread(file,"纸短情长"));
        Thread thread2= new Thread(new FileThread(file,"最终的我们没有走到一起"));
        thread.start();
        thread1.start();
        thread2.start();


    }
    private static void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

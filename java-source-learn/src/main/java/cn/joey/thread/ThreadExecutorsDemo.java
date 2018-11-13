package cn.joey.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @auther liujiji
 * @date 2018/11/11 15:14
 */
@Slf4j
public class ThreadExecutorsDemo {
    public static void main(String[] args) {
        ExecutorService  executor = Executors.newFixedThreadPool(3);
        for(int i=0;i<10;i++){
            final int count = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("task:{}",count);
                }
            });
        }
        executor.shutdown();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("ssssss");
            }
        },new Date(),5*1000);

    }
}

package cn.joey.thread;

import java.util.concurrent.Callable;

/**
 * @author liujiji
 * @date 2018/10/10 16:53
 */
public class CallableDemo implements Callable<Integer>{

    private int sum;
    private int count=5000;
    @Override
    public Integer call() throws Exception {
        System.out.println("Callable子线程开始计算！");
        Thread.sleep(2000);
        for (int i = 0; i < count ; i++) {
            sum = sum+i;
        }
        System.out.println("Callable子线程计算结束");
        Thread thread = Thread.currentThread();
        System.out.println(thread);
        return sum;
    }
}

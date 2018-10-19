package cn.joey.thread;

import java.util.concurrent.*;

/**
 * @auther liujiji
 * @date 2018/10/10 16:55
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService es = Executors.newSingleThreadExecutor();

        Callable callable = new CallableDemo();

        Future<Integer> future = es.submit(callable);

        es.shutdown();

        try {
            Thread.sleep(2000);
            System.out.println("主线程在执行其他任务");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (future.get() != null) {
            System.out.println("future.get()--->"+future.get());
        }else{
            System.out.println("没有在future.get()获取数据");
        }
        System.out.println("主线程执行完成！");
    }
}

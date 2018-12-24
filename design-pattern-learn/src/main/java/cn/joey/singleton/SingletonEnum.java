package cn.joey.singleton;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @auther liujiji
 * @date 2018/12/19 14:16
 */
public enum SingletonEnum {
    INSTANCE;

    SingletonEnum(){}
    public void printHello(){
        //实现方法
        System.out.println("Hello");
    }

    public static void main(String[] args) throws InterruptedException {
        Integer loopNum = 1000000;
        final Set<Object> instanceSet = new HashSet<Object>();
        ExecutorService executorService= Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch=new CountDownLatch(loopNum);
        for(int i=0;i<loopNum;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
//                    SingletonEnum singletonEnum = SingletonEnum.INSTANCE;
//                    singletonEnum.printHello();
                    SingletonDemo instance = SingletonDemo.getInstance();
                    instanceSet.add(instance);
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();

        System.out.println("查看实例的个数是:"+instanceSet);
    }
}

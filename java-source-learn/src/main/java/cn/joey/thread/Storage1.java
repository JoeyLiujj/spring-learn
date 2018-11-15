package cn.joey.thread;

import java.util.LinkedList;
import java.util.List;

public class Storage1 implements AbstractStorage{
    //仓库最大数量；
    private final int MAX_SIZE = 100;
    //仓库存储的载体
    private LinkedList list = new LinkedList();

    @Override
    public void produce(int sum) {
        synchronized (list) {
            while (list.size() + sum > MAX_SIZE) {
                System.out.println("【要生产的产品数量】"+sum+"\t 【库存量】："+
                list.size()+"\t 暂时不能执行生产任务！");

                try {
                    //条件不满足，生产阻塞
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < sum; i++) {
                list.add(new Object());
            }

            System.out.println("【已经生产产品数】："+sum+"\t 【现仓储量】："+list.size());
            list.notifyAll();
        }
    }
    //消费产品
    @Override
    public void consume(int num){
        synchronized (list) {
            while (list.size() < num) {
                System.out.println("【要消费的产品数量】："+num+"\t 【库存量】："+list.size()+"\t 暂时不能执行生产任务！");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for(int i=0;i<num;i++) {
                list.remove();
            }
            System.out.println("【已经消费产品数】："+num+"\t 【现仓储量为】："+list.size());
            list.notifyAll();
        }
    }

}

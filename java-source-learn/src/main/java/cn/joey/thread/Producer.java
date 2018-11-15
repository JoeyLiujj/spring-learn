package cn.joey.thread;

import java.util.Queue;

public class Producer implements Runnable {

    private final Queue<String> queue;

    private final int maxSize;

    public Producer(Queue<String> queue,int maxSize){
        this.queue = queue;
        this.maxSize = maxSize;
    }
    @Override
    public void run() {
        produce();
    }

    private void produce(){
        try {
            while (true) {
                synchronized (queue) {
                    if (queue.size() == maxSize) {
                        System.out.println("生产者：仓库满了，等待消费者消费");
                        queue.wait();
                    }
                    System.out.println("生产者：" + queue.add("product"));
                    queue.notifyAll();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

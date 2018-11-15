package cn.joey.thread;

import java.util.Queue;

public class Consumer implements Runnable {
    private final Queue<String> queue;

    public Consumer(Queue<String> queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        consume();
    }
    private void consume(){
        synchronized (queue){
            try {
                while (true) {
                    if (queue.isEmpty()) {
                        System.out.println("消费者：仓库空了，等待生产者生产");
                        queue.wait();
                    }
                    System.out.println("消费者：" + queue.remove());
                    queue.notifyAll();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

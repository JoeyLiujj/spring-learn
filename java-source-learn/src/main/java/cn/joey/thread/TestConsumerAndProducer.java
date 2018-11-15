package cn.joey.thread;

import java.util.LinkedList;
import java.util.Queue;

public class TestConsumerAndProducer {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<String>();
        int maxSize=100;
        Thread producer = new Thread(new Producer(queue, maxSize));
        Thread consumer = new Thread(new Consumer(queue));
        producer.start();
        consumer.start();
        System.out.println("我可能会在中间执行");
    }
}

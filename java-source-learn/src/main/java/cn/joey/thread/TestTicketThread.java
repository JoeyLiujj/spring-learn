package cn.joey.thread;

import java.util.LinkedList;
import java.util.Queue;

public class TestTicketThread {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new TicketThread(10));
        Thread thread2 = new Thread(new TicketThread(10));
        thread1.start();
        thread2.start();
    }
}

package cn.joey.thread;

import java.util.Queue;

public class TicketThread implements Runnable {

    private int ticket;

    public TicketThread(int ticket){
        this.ticket = ticket;
    }

    @Override
    public void run() {
        while(ticket==0){
            System.out.println("票已经卖完了！");
            break;
        }
        System.out.println("已卖出"+ticket+"张票");
        ticket--;
    }
}

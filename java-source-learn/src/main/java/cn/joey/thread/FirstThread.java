package cn.joey.thread;

public class FirstThread implements Runnable {
    private WRFile wr;
    public FirstThread(WRFile wr) {
        this.wr = wr;
    }
    @Override
    public void run() {
        while (true) {
            wr.read1();
        }
    }
}

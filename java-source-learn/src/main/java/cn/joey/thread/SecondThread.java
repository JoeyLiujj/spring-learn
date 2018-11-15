package cn.joey.thread;

public class SecondThread implements Runnable {
    private WRFile wr;

    public SecondThread(WRFile wr) {
        this.wr=wr;
    }
    @Override
    public void run() {
        while (true) {
            wr.read2();
        }
    }
}

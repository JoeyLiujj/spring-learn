package cn.joey.thread;

public class Consumer1 extends Thread {
    //每次生产的数量
    private int num;
    private AbstractStorage storage;

    public Consumer1(AbstractStorage storage) {
        this.storage = storage;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        consume(num);
    }

    //调用仓库Storage的生产函数
    private void consume(int sum) {
        storage.consume(sum);
    }
}

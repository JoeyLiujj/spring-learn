package cn.joey.thread;

public class Producer1 extends Thread {
    //每次生产的数量
    private int num;
    private AbstractStorage storage;

    public Producer1(AbstractStorage storage) {
        this.storage = storage;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        produce(num);
    }

    //调用仓库Storage的生产函数
    private void produce(int sum) {
        storage.produce(sum);
    }
}

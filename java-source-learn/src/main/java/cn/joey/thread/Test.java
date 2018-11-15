package cn.joey.thread;

public class Test {
    public static void main(String[] args) {
        AbstractStorage storage = new Storage1();

        Producer1 producer1 = new Producer1(storage);
        Producer1 producer2 = new Producer1(storage);
        Producer1 producer3 = new Producer1(storage);
        Producer1 producer4 = new Producer1(storage);
        Producer1 producer5 = new Producer1(storage);
        Producer1 producer6 = new Producer1(storage);
        Producer1 producer7 = new Producer1(storage);

        Consumer1 consumer1 = new Consumer1(storage);
        Consumer1 consumer2 = new Consumer1(storage);
        Consumer1 consumer3 = new Consumer1(storage);


        producer1.setNum(10);
        producer2.setNum(10);
        producer3.setNum(10);
        producer4.setNum(10);
        producer5.setNum(10);
        producer6.setNum(10);
        producer7.setNum(80);

        consumer1.setNum(50);
        consumer2.setNum(20);
        consumer3.setNum(30);

        consumer1.start();
        consumer2.start();
        consumer3.start();

        producer1.start();
        producer2.start();
        producer3.start();
        producer4.start();
        producer5.start();
        producer6.start();
        producer7.start();
    }
}

package cn.joey.singleton;

/**
 * @auther liujiji
 * @date 2018/11/27 9:11
 *
 * 此种是内部类的形式，效率高而且线程安全
 * 不像饿汉式和懒汉式，还有一种是注册登记式(包括枚举式)，
 */
public class SingletonDemo {

    public SingletonDemo(){

    }

    public static final SingletonDemo getInstance(){
        return SingletonHandler.demo;
    }

    static class SingletonHandler {
        public static SingletonDemo demo = new SingletonDemo();
    }
}

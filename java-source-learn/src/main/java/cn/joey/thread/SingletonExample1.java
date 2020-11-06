package cn.joey.thread;

import com.mongodb.annotations.NotThreadSafe;

/**
 * @auther liujiji
 * @date 2018/10/26 12:28
 */
@NotThreadSafe
public class SingletonExample1 {
    private SingletonExample1() {

    }

    //静态代码块和变量的代码顺序
    //单例对象
    private static SingletonExample1 instance = null;

    static {
        instance = new SingletonExample1();
    }

    //静态的工厂方法
    public static SingletonExample1 getInstance() {
//        if (instance == null) {
//            instance = new SingletonExample1();
//        }
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance());
    }
}

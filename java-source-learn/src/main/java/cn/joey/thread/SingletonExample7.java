package cn.joey.thread;

import com.mongodb.annotations.ThreadSafe;

/**
 * @auther liujiji
 * @date 2018/10/26 12:28
 * //枚举模式 是最安全的
 */
@ThreadSafe
public class SingletonExample7 {
    private SingletonExample7() {

    }
    //静态的工厂方法
    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getSingleton();
    }

    private enum Singleton {
        INSTANCE;
        private SingletonExample7 singleton;

        //JVM保证这个方法只被调用一次
        Singleton() {
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getSingleton() {
            return singleton;
        }
    }
}

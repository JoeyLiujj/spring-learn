package cn.joey.thread.reentrant;

/**
 * @auther liujiji
 * @date 2019/5/29 16:15
 */
public class ReentrantLockTest {
    public static void main(String[] args) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader.getClass());
        System.out.println(ClassLoader.getSystemClassLoader());
    }
}

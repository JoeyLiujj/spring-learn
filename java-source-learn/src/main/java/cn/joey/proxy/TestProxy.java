package cn.joey.proxy;

import java.lang.reflect.Proxy;

/**
 * @auther liujiji
 * @date 2018/12/19 9:23
 */
public class TestProxy {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        CustomInvocationHandler handler = new CustomInvocationHandler(new HelloWorldImpl());
        HelloWorld helloWorld = (HelloWorld) Proxy.newProxyInstance(TestProxy.class.getClassLoader(), new Class[]{HelloWorld.class}, handler);
        //当调用代理类的方法时，实际上在代理类的字节码文件中会调用handler的invoke方法
        //即调用CustomInvocationHandler的invoke方法
        helloWorld.sayHello("Joey");
    }
}

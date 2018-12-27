package cn.joey.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CustomInvocationHandler implements InvocationHandler {
    private Object target;

    public CustomInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //其中proxy为生成的代理类对象，method为被代理的实际的方法，args为方法参数值
        System.out.println("Before invocation");

        System.out.println(proxy.getClass());
        Object retVal = method.invoke(target, args);
        System.out.println("After invocation");
        return retVal;
    }

    public HelloWorld newInstance(){
        HelloWorld hw=(HelloWorld) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{HelloWorld.class}, this);
        return hw;
    }
}

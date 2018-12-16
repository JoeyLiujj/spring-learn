package cn.joey.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @auther liujiji
 * @date 2018/12/13 14:10
 */
//默认使用JDK的动态代理
public class GPAopProxy implements InvocationHandler{

    private GPAopConfig config;
    private Object target;

    public void setConfig(GPAopConfig config){
        this.config = config;
    }

    //把原生的对象传进来
    public Object getProxy(Object instance){
        this.target = instance;
        Class<?> clazz = instance.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //完成增强的目的
        Method m = this.target.getClass().getMethod(method.getName(),method.getParameterTypes());

        if (config.contains(m)) {
            GPAopConfig.GPAspect aspect = config.get(m);
            aspect.getPoints()[0].invoke(aspect.getAspect());
        }

        Object obj = method.invoke(this.target,args);

        if (config.contains(m)) {
            GPAopConfig.GPAspect aspect = config.get(m);
            aspect.getPoints()[1].invoke(aspect.getAspect());
        }
        return obj;
    }
}

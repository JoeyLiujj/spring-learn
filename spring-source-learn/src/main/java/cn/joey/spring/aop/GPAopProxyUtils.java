package cn.joey.spring.aop;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * @auther liujiji
 * @date 2018/12/13 15:20
 */
public class GPAopProxyUtils {

    public static Object getTargetObject(Object proxy) throws NoSuchFieldException, IllegalAccessException {
        //先判断传进来的对象，是不是一个代理过的对象
        if(isAopProxy(proxy)) return proxy;
        return getProxyTargetObject(proxy);

    }

    private static boolean isAopProxy(Object proxy) {
        return Proxy.isProxyClass(proxy.getClass());
    }

    private static Object getProxyTargetObject (Object proxy) throws NoSuchFieldException, IllegalAccessException {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        GPAopProxy aopProxy = (GPAopProxy)h.get(proxy);
        Field target = aopProxy.getClass().getDeclaredField("target");
        target.setAccessible(true);
        return target.get(aopProxy);
    }
}

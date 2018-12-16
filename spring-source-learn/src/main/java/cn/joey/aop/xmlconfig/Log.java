package cn.joey.aop.xmlconfig;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;


public class Log implements MethodBeforeAdvice{
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println(method.getName()+"方法被执行");
    }
}

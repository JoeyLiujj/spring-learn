package cn.joey.aop.testProxyFactoryBean;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.Interceptor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;

import java.lang.reflect.Method;

/**
 * @auther liujiji
 * @date 2018/12/14 15:22
 */
public class MyInterceptor implements MethodInterceptor {

    public Object invoke(MethodInvocation i) throws Throwable {
        Method method = i.getMethod();
        System.out.println("在拦截之前答应方法名："+method.getName());
        return i.proceed();
    }

}

package com.joey.mybatis.ver2.interceptor;

import com.joey.mybatis.ver2.handler.GP2StatementHandler;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @auther liujiji
 * @date 2018/12/5 11:08
 */
@Intercepts({@Signature(type= GP2StatementHandler.class,method="prepareStatement",args={String.class,Object.class})})
public class GP2SecondInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        System.out.println("GP2SecondInterceptor+"+method.getName());
        Object[] args = invocation.getArgs();
        for(Object arg:args){
            System.out.println(arg);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}

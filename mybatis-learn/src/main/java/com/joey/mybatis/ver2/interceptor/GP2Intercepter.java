package com.joey.mybatis.ver2.interceptor;

import com.joey.mybatis.ver2.handler.GP2StatementHandler;
import org.apache.ibatis.plugin.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @auther liujiji
 * @date 2018/12/3 15:52
 */
@Intercepts({@Signature(type= GP2StatementHandler.class,method="prepareStatement",args={String.class,Object.class})})
public class GP2Intercepter implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        System.out.println("GP2Interceptor+"+method.getName());
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

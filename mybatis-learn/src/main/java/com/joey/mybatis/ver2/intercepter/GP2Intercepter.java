package com.joey.mybatis.ver2.intercepter;

import com.joey.mybatis.ver2.executor.impl.GP2SimpleExecutor;
import com.joey.mybatis.ver2.handler.GP2StatementHandler;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.InvocationTargetException;
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
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}

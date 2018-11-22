package cn.joey.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @auther liujiji
 * @date 2018/11/22 14:37
 */
@Intercepts({@Signature(type= Executor.class,method="query",args={MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class})})
public class CustomInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        System.out.println(method.getName());
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

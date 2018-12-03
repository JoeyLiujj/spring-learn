package com.joey.mybatis.ver2;

import com.joey.mybatis.ver2.annotation.GP2Mapper;
import com.joey.mybatis.ver2.annotation.GP2Select;
import com.joey.mybatis.ver2.executor.GP2Executor;
import com.joey.mybatis.ver2.executor.impl.GP2SimpleExecutor;
import com.joey.mybatis.ver2.handler.GP2StatementHandler;
import com.joey.mybatis.ver2.handler.impl.GP2PrepareStatementHandler;
import com.joey.mybatis.ver2.intercepter.GP2Intercepter;
import com.joey.mybatis.ver2.mapper.GP2TestMapper;
import com.joey.mybatis.ver2.session.GP2SqlSession;
import org.apache.ibatis.plugin.InterceptorChain;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther liujiji
 * @date 2018/12/3 9:47
 */
public class GP2Configuration {
    static final Map<String,String> registerMap = new HashMap<>();
    protected static final InterceptorChain interceptorChain = new InterceptorChain();
    public static String namespace = "com.joey.mybatis.ver2.mapper.GP2TestMapper";

    public <T> T getMapper(GP2SqlSession sqlSession, Class type) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{GP2TestMapper.class},new GP2MethodProxy(sqlSession,type));
    }

    public GP2Executor newExecutor(){
        return new GP2SimpleExecutor(this);
    }

    static {
        try {
            MapperRegister.register();
            interceptorChain.addInterceptor(new GP2Intercepter());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Object newStatementHandler() {
        GP2StatementHandler statementHandler = new GP2PrepareStatementHandler();
        GP2StatementHandler handler = (GP2StatementHandler)interceptorChain.pluginAll(statementHandler);
        return handler;
    }

    static class MapperRegister{
        public static void register() throws ClassNotFoundException {
            Class<?> clazz = Class.forName(namespace);
            if(clazz.isAnnotationPresent(GP2Mapper.class)){
                Method[] methods = clazz.getMethods();
                for(Method method:methods){
                    if(method.isAnnotationPresent(GP2Select.class)){
                        GP2Select annotation = method.getAnnotation(GP2Select.class);
                        String sql = annotation.value();
                        registerMap.put(method.getName(),sql);
                    }
                }
            }
        }
    }
}

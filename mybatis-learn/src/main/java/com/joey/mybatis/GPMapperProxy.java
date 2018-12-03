package com.joey.mybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class GPMapperProxy implements InvocationHandler {

    private GPSqlSession sqlSession;

    public GPMapperProxy(GPSqlSession sqlSession){
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getDeclaringClass().getName().equals(GPConfiguration.TestMaperXml.namespace)){
            String sql = GPConfiguration.TestMaperXml.methodSql.get(method.getName());
            return sqlSession.selectOne(sql,String.valueOf(args[0]));
        }
        return method.invoke(this,args);
    }
}
